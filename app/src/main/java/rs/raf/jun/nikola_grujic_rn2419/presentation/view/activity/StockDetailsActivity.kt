package rs.raf.jun.nikola_grujic_rn2419.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import rs.raf.jun.nikola_grujic_rn2419.R
import rs.raf.jun.nikola_grujic_rn2419.data.model.Stock
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.DetailsViewModel
import rs.raf.jun.nikola_grujic_rn2419.presentation.viewModel.DetailsViewModelImpl

class StockDetailsActivity : AppCompatActivity() {
    private val viewModel: DetailsViewModel = DetailsViewModelImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_details)

        init()
    }

    private fun init() {
        showProgressBar()
        val symbol = intent.getStringExtra("symbol") ?: return
        supportActionBar?.title = "Stock details - $symbol"

        // delete when the API starts working, and replace the current one
        if (symbol != "T") {
            Toast.makeText(this, "Symbol must be T", Toast.LENGTH_SHORT).show()
            finish()
        }

        fetchData(symbol)
    }

    private fun fetchData(symbol: String) {
        viewModel.fetchStock(symbol)
        viewModel.stock.observe(this) { response ->
            hideProgressBar()

            if (response != null && response.instrumentId != "INVALID") {
                //Log.d("RESPONSE DETAILS", response.instrumentId)
                initUi(response)
            }
            else Toast.makeText(this, "Request to the server failed!",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun initUi(stock: Stock) {
        val symbol: TextView = findViewById(R.id.symbolTv)
        val curVal: TextView = findViewById(R.id.currentValTv)
        val currency: TextView = findViewById(R.id.currencyTv)
        val open: TextView = findViewById(R.id.openTv)
        val close: TextView = findViewById(R.id.closeTv)
        val bid: TextView = findViewById(R.id.bidTv)
        val ask: TextView = findViewById(R.id.askTv)
        val alpha: TextView = findViewById(R.id.alphaTv)
        val beta: TextView = findViewById(R.id.betaTv)
        val sharp: TextView = findViewById(R.id.sharpTv)
        val mktCup: TextView = findViewById(R.id.mktCapTv)
        val eps: TextView = findViewById(R.id.epsTv)
        val ebit: TextView = findViewById(R.id.ebitTv)

        symbol.text = "Stock symbol: " + stock.symbol
        curVal.text = "Last value: " + stock.last
        currency.text = "Currency: " + stock.currency
        open.text = "OPEN: " + stock.open
        close.text = "CLOSE: " + stock.close
        bid.text = "BID: " + stock.bid
        ask.text = "ASK: " + stock.ask
        alpha.text = "ALPHA: " + stock.metrics.alpha
        beta.text = "BETA: " + stock.metrics.beta
        sharp.text = "SHARP: " + stock.metrics.sharp
        mktCup.text = "MK CUP: " + stock.metrics.marketCup
        eps.text = "EPS: " + stock.metrics.eps
        ebit.text = "EBIT: " + stock.metrics.ebit

        initChart(stock)
    }

    private fun initChart(stock: Stock) {
        // TODO: create chart
    }

    private fun showProgressBar() {
        val progress: ProgressBar = findViewById(R.id.detailsProgBar)
        progress.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        val progress: ProgressBar = findViewById(R.id.detailsProgBar)
        progress.visibility = View.GONE
    }
}