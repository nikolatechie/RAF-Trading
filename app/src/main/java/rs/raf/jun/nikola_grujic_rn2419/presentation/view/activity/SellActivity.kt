package rs.raf.jun.nikola_grujic_rn2419.presentation.view.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import rs.raf.jun.nikola_grujic_rn2419.R
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.PortfolioHistory
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.BuySellViewModel
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.BuySellViewModelFactory
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.BuySellViewModelImpl
import java.lang.Exception

class SellActivity : AppCompatActivity() {
    private lateinit var viewModel: BuySellViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell)

        init()
    }

    private fun init() {
        supportActionBar?.title = "Sell"

        // view model
        val viewModelFactory = BuySellViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[
                BuySellViewModelImpl::class.java
        ]

        initUi()
    }


    private fun initUi() {
        val symbol = intent.getStringExtra("symbol")!!
        val name = intent.getStringExtra("name")
        val nameTv: TextView = findViewById(R.id.sellNameTv)
        nameTv.text = "Name: $name"

        val numOfShares: TextView = findViewById(R.id.numOfSharesTv)
        val username = getUsername()
        val boughtStock = viewModel.getBoughtStock(username, symbol)

        if (boughtStock == null) {
            Toast.makeText(this, "Error! You don't have this stock!", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        numOfShares.text = "Number of shares: " + boughtStock.amount.toString()

        val toggle: Switch = findViewById(R.id.sellToggle)
        toggle.text = "Sell all $symbol shares"

        toggle.setOnClickListener {
            val editText: EditText = findViewById(R.id.sellEditText)

            if (toggle.isChecked) {
                editText.setText("")
                editText.hint = boughtStock.amount.toString()
                editText.isFocusable = false
            }
            else {
                editText.isFocusable = true
                editText.hint = "Enter number of shares"
            }
        }

        val sellBtn: Button = findViewById(R.id.sellStockBtn)
        sellBtn.setOnClickListener {
            val boughtStock = viewModel.getBoughtStock(username, symbol)!!

            if (toggle.isChecked) {
                // sell all shares
                sellShares(username, symbol, boughtStock.amount, boughtStock)
                Toast.makeText(this, "Sold successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
            else {
                // sell the enter number of shares
                val editText: EditText = findViewById(R.id.sellEditText)

                if (editText.text.isEmpty())
                    Toast.makeText(this, "Enter number of shares", Toast.LENGTH_SHORT).show()
                else {
                    try {
                        val enteredAmount = editText.text.toString().toInt()

                        if (enteredAmount < 1 || enteredAmount > boughtStock.amount)
                            Toast.makeText(this, "Invalid number!", Toast.LENGTH_SHORT).show()
                        else {
                            sellShares(username, symbol, enteredAmount, boughtStock)
                            Toast.makeText(this, "Sold successfully", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                    catch (e: Exception) {
                        Toast.makeText(this, "You have to enter a valid number!",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun sellShares(username: String, symbol: String, amount: Int, stock: BoughtStock) {
        var accInfo = viewModel.getAccountInfo(username)

        if (accInfo == null) {
            accInfo = AccountInfo(username, 10000.toDouble(), 0.toDouble())
            viewModel.addAccountInfo(accInfo)
        }

        accInfo.balance += stock.price * amount.toDouble()
        accInfo.portfolio -= stock.price * amount.toDouble()

        if (accInfo.balance < 1)
            accInfo.balance = 0.toDouble()

        if (accInfo.portfolio < 1)
            accInfo.portfolio = 0.toDouble()

        viewModel.addAccountInfo(accInfo)
        viewModel.addPortfolioHistory(PortfolioHistory(0, accInfo.username, accInfo.portfolio))
        viewModel.addBoughtStock(BoughtStock(stock.id, username, symbol, stock.name, stock.price,
            stock.amount - amount, stock.change))

        if (stock.amount == amount)
            viewModel.deleteStock(accInfo.username)
    }

    private fun getUsername(): String {
        val sp: SharedPreferences = getSharedPreferences(
            "userInfo",
            AppCompatActivity.MODE_PRIVATE
        )
        return sp.getString("username", "")!!
    }
}