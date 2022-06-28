package rs.raf.jun.nikola_grujic_rn2419.presentation.view.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import rs.raf.jun.nikola_grujic_rn2419.R
import rs.raf.jun.nikola_grujic_rn2419.data.model.Bar
import rs.raf.jun.nikola_grujic_rn2419.data.model.BoughtStock
import rs.raf.jun.nikola_grujic_rn2419.data.model.Chart
import rs.raf.jun.nikola_grujic_rn2419.presentation.view.activity.StockDetailsActivity

class PortfolioRecyclerAdapter(private var list: ArrayList<BoughtStock>,
                               private var chartData: Chart,
                               private var activity: Activity) :
    RecyclerView.Adapter<PortfolioRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var symbol: TextView = view.findViewById(R.id.pSymbol)
        var name: TextView = view.findViewById(R.id.pStockName)
        var lastPrice: TextView = view.findViewById(R.id.pLastPrice)
        var chart: LineChart = view.findViewById(R.id.pStockChart)
        var amount: TextView = view.findViewById(R.id.pAmount)
        var card: ConstraintLayout = view.findViewById(R.id.cardPortfolio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_view_portfolio,
            parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.symbol.text = "Symbol: " + list[position].symbol
        holder.name.text = "Name: " + list[position].name
        holder.lastPrice.text =
            "Last price: " + list[position].price.toString()

        var change: Double = 0.toDouble()

        for (stock: BoughtStock in list) {
            if (stock.symbol == list[position].symbol) {
                holder.amount.text = "Amount: " + stock.amount
                change = stock.change
                break
            }
        }

        // chart
        val entries = ArrayList<Entry>()

        for (bar: Bar in chartData.bars)
            entries.add(Entry(entries.size.toFloat(), (bar.price).toFloat()))

        val set = LineDataSet(entries, "Stock")

        if (change < 0f) {
            set.color = Color.RED
            set.setCircleColor(Color.RED)
        }
        else {
            set.color = Color.GREEN
            set.setCircleColor(Color.GREEN)
        }

        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(set)
        val data = LineData(dataSet)
        holder.chart.data = data
        holder.chart.invalidate()

        // stock details
        holder.card.setOnClickListener {
            val intent = Intent(activity, StockDetailsActivity::class.java)
            intent.putExtra("symbol", list[position].symbol)
            intent.putExtra("change", change)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}