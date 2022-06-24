package rs.raf.jun.nikola_grujic_rn2419.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rs.raf.jun.nikola_grujic_rn2419.R
import rs.raf.jun.nikola_grujic_rn2419.data.model.Quote

class StocksRecyclerAdapter(private var list: ArrayList<Quote>) :
    RecyclerView.Adapter<StocksRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var symbol: TextView = view.findViewById(R.id.symbolRight)
        var name: TextView = view.findViewById(R.id.stockName)
        var lastPrice: TextView = view.findViewById(R.id.lastPrice)
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
        holder.lastPrice.text =  "Last price: " + list[position].last.toString() + list[position].currency
    }

    override fun getItemCount(): Int {
        return list.size
    }
}