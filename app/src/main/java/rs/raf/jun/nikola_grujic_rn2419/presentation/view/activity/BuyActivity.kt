package rs.raf.jun.nikola_grujic_rn2419.presentation.view.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun.nikola_grujic_rn2419.R
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.BuySellViewModel
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.BuySellViewModelImpl
import java.lang.Exception

class BuyActivity : AppCompatActivity() {
    private val viewModel: BuySellViewModel by viewModel<BuySellViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        init()
    }

    private fun init() {
        supportActionBar?.title = "Buy"
        initUi()
    }

    private fun initUi() {
        val name = intent.getStringExtra("name")
        val nameTv: TextView = findViewById(R.id.buyNameTv)
        nameTv.text = "Name: $name"

        val toggle: Switch = findViewById(R.id.buyToggle)
        toggle.setOnClickListener {
            val editText: EditText = findViewById(R.id.buyEditText)

            if (toggle.isChecked)
                editText.hint = "Enter number of shares"
            else
                editText.hint = "Enter a cash amount"
        }

        val accBalance: TextView = findViewById(R.id.accBalanceTv)
        val username = getUsername()

        viewModel.getAccountInfo(username)
        viewModel.accResponse.observe(this) { response ->
            var accInfo = response

            if (accInfo == null) {
                accInfo = AccountInfo(username, 10000.toDouble(), 0.toDouble())
                viewModel.addAccountInfo(accInfo)
            }

            accBalance.text = "Account balance: " + roundToTwoDecimals(accInfo.balance.toString())

            val buyBtn: Button = findViewById(R.id.buyStockBtn)
            buyBtn.setOnClickListener {
                val editText: EditText = findViewById(R.id.buyEditText)

                if (editText.text.toString().isEmpty())
                    Toast.makeText(this, "The value must not be empty", Toast.LENGTH_SHORT).show()
                else {
                    try {
                        if (toggle.isChecked)
                            buyByShares(editText.text.toString().toInt(), accInfo)
                        else
                            buyByMoney(editText.text.toString().toDouble(), accInfo)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(
                            this,
                            "The entered value must be a number",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    // BUYING - first option
    private fun buyByShares(numOfShares: Int, accInfo: AccountInfo) {
        if (numOfShares < 1) {
            Toast.makeText(this, "Number must be greater than zero", Toast.LENGTH_SHORT).show()
            return
        }

        val price: Double = intent.getDoubleExtra("last", 0.toDouble())

        if (numOfShares * price > accInfo.balance) {
            Toast.makeText(this, "You don't have enough money", Toast.LENGTH_SHORT).show()
            return
        }

        val deduct: Double = numOfShares * price
        accInfo.balance -= deduct
        accInfo.portfolio += deduct

        viewModel.addAccountInfo(accInfo)
        viewModel.addPortfolioHistory(PortfolioHistory(0, accInfo.username, accInfo.portfolio))
        addBoughtStock(accInfo.username, numOfShares)
        Toast.makeText(this, "Bought successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }

    // BUYING - second option
    private fun buyByMoney(money: Double, accInfo: AccountInfo) {
        if (money < 1 || money > accInfo.balance) {
            Toast.makeText(this, "Invalid amount", Toast.LENGTH_SHORT).show()
            return
        }

        val price: Double = intent.getDoubleExtra("last", 0.toDouble())
        val amount: Int = (money / price).toInt()

        if (amount == 0) {
            Toast.makeText(this, "Invalid value", Toast.LENGTH_SHORT).show()
            return
        }

        val deduct: Double = amount * price
        accInfo.balance -= deduct
        accInfo.portfolio += deduct

        viewModel.addAccountInfo(accInfo)
        viewModel.addPortfolioHistory(PortfolioHistory(0, accInfo.username, accInfo.portfolio))
        addBoughtStock(accInfo.username, amount)
        Toast.makeText(this, "Bought successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun addBoughtStock(username: String, amount: Int) {
        val change: Double = intent.getDoubleExtra("change", 0.toDouble())
        val symbol = intent.getStringExtra("symbol")!!

        viewModel.getBoughtStock(username, symbol)
        viewModel.stockResponse.observe(this) { boughtStock ->
            if (boughtStock != null) {
                boughtStock.amount += amount
                viewModel.addBoughtStock(boughtStock)
            }
            else {
                val stock = BoughtStock(
                    0, username, symbol,
                    intent.getStringExtra("name")!!, intent.getDoubleExtra("last", 0.toDouble()),
                    amount, change
                )

                viewModel.addBoughtStock(stock)
            }
        }
    }

    private fun roundToTwoDecimals(num: String): String {
        var ans = ""

        for (i in num.indices) {
            if (num[i] == '.') {
                ans += "."

                ans += try {
                    (num[i+1].toString() + num[i+2])
                } catch (e: Exception) {
                    "00"
                }

                break
            }
            else ans += num[i]
        }

        return ans
    }

    private fun getUsername(): String {
        val sp: SharedPreferences = getSharedPreferences(
            "userInfo",
            AppCompatActivity.MODE_PRIVATE
        )
        return sp.getString("username", "")!!
    }
}