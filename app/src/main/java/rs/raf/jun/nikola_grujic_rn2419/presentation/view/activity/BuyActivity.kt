package rs.raf.jun.nikola_grujic_rn2419.presentation.view.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import rs.raf.jun.nikola_grujic_rn2419.R
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.BuyViewModel
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.BuyViewModelFactory
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.BuyViewModelImpl

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
            // todo
        }
    }

    private fun getUsername(): String {
        val sp: SharedPreferences = getSharedPreferences(
            "userInfo",
            AppCompatActivity.MODE_PRIVATE
        )
        return sp.getString("username", "")!!
    }
}