package rs.raf.jun.nikola_grujic_rn2419.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import rs.raf.jun.nikola_grujic_rn2419.R

class BuyActivity : AppCompatActivity() {
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
    }
}