package rs.raf.jun.nikola_grujic_rn2419.presentation.view.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModelProvider
import rs.raf.jun.nikola_grujic_rn2419.R
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.BuyViewModel
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.BuyViewModelFactory
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.BuyViewModelImpl
import java.lang.Exception

class BuyActivity : AppCompatActivity() {
    private lateinit var viewModel: BuyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        init()
    }

    private fun init() {
        supportActionBar?.title = "Buy"

        // view model
        val viewModelFactory = BuyViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[
                BuyViewModelImpl::class.java
        ]

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
        var accInfo = viewModel.getAccountInfo(username)

        if (accInfo == null) {
            accInfo = AccountInfo(username, 10000.toDouble(), 0.toDouble())
            viewModel.addAccountInfo(accInfo)
        }

        accBalance.text = "Account balance: " + accInfo.balance.toString()

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
                }
                catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "The entered value must be a number", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

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
        finish()
    }

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
        finish()
    }

    private fun getUsername(): String {
        val sp: SharedPreferences = getSharedPreferences(
            "userInfo",
            AppCompatActivity.MODE_PRIVATE
        )
        return sp.getString("username", "")!!
    }
}