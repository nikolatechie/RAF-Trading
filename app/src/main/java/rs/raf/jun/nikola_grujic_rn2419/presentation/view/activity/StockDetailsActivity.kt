package rs.raf.jun.nikola_grujic_rn2419.presentation.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import rs.raf.jun.nikola_grujic_rn2419.R
import rs.raf.jun.nikola_grujic_rn2419.data.model.Bar
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
            else {
                Toast.makeText(this, "Request to the server failed!",
                    Toast.LENGTH_SHORT).show()

                finish()
            }
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

        initListeners(stock)
        initChart(stock)
    }

    private fun initListeners(stock: Stock) {
        val buyBtn: Button = findViewById(R.id.buyBtn)
        val sellBtn: Button = findViewById(R.id.sellBtn)

        buyBtn.setOnClickListener {
            val intent = Intent(this, BuyActivity::class.java)
            intent.putExtra("id", stock.instrumentId)
            intent.putExtra("name", stock.name)
            intent.putExtra("symbol", stock.symbol)
            intent.putExtra("last", stock.last)
            startActivity(intent)
        }

        sellBtn.setOnClickListener {
            val intent = Intent(this, SellActivity::class.java)
            intent.putExtra("id", stock.instrumentId)
            intent.putExtra("name", stock.name)
            intent.putExtra("symbol", stock.symbol)
            intent.putExtra("last", stock.last)
            startActivity(intent)
        }
    }

    private fun initChart(stock: Stock) {
        val chart: LineChart = findViewById(R.id.stockChart)
        val entries = ArrayList<Entry>()

        for (bar: Bar in stock.chart.bars)
            entries.add(Entry(entries.size.toFloat(), (bar.price).toFloat()))

        val set = LineDataSet(entries, "Stock")

        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(set)
        val data = LineData(dataSet)
        chart.data = data
        chart.invalidate()
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