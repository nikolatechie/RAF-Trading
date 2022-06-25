package rs.raf.jun.nikola_grujic_rn2419.presentation.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import rs.raf.jun.nikola_grujic_rn2419.R
import rs.raf.jun.nikola_grujic_rn2419.data.model.Bar
import rs.raf.jun.nikola_grujic_rn2419.data.model.Quote

class StocksRecyclerAdapter(private var list: ArrayList<Quote>) :
    RecyclerView.Adapter<StocksRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var symbol: TextView = view.findViewById(R.id.symbolRight)
        var name: TextView = view.findViewById(R.id.stockName)
        var lastPrice: TextView = view.findViewById(R.id.lastPrice)
        var chart: LineChart = view.findViewById(R.id.stockChart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_view_stocks,
            parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.symbol.text = list[position].symbol
        holder.name.text = "Name: " + list[position].name
        holder.lastPrice.text =
            "Last price: " + list[position].last.toString() + list[position].currency

        val entries = ArrayList<Entry>()

        for (bar: Bar in list[position].chart.bars)
            entries.add(Entry(entries.size.toFloat(), (bar.price).toFloat()))

        val set = LineDataSet(entries, "Stock")

        if (list[position].changeFromPreviousClose < 0f) {
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
    }

    override fun getItemCount(): Int {
        return list.size
    }
}