package rs.raf.jun.nikola_grujic_rn2419.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rs.raf.jun.nikola_grujic_rn2419.R
import rs.raf.jun.nikola_grujic_rn2419.data.model.News

// sa Android Developers
class NewsRecyclerAdapter(private var list: ArrayList<News>) :
    RecyclerView.Adapter<NewsRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var dateTv: TextView = view.findViewById(R.id.dateTv)
        var titleTv: TextView = view.findViewById(R.id.titleTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_news,
            parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.dateTv.text = list[position].date
        holder.titleTv.text = list[position].title
    }

    override fun getItemCount(): Int {
        return list.size
    }
}