package rs.raf.jun.nikola_grujic_rn2419.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rs.raf.jun.nikola_grujic_rn2419.R

class StockDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_details)

        init()
    }

    private fun init() {
        val symbol = intent.getStringExtra("symbol")
        supportActionBar?.title = "Stock details - $symbol"
    }
}