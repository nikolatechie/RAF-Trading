package rs.raf.jun.nikola_grujic_rn2419.presentation.view.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import rs.raf.jun.nikola_grujic_rn2419.R
import rs.raf.jun.nikola_grujic_rn2419.data.model.News
import rs.raf.jun.nikola_grujic_rn2419.module.GlideApp

// sa Android Developers
class NewsRecyclerAdapter(private var list: ArrayList<News>,
    private var activity: Activity) :
    RecyclerView.Adapter<NewsRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var dateTv: TextView = view.findViewById(R.id.dateTv)
        var titleTv: TextView = view.findViewById(R.id.titleTv)
        var card: LinearLayout = view.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_news,
            parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dateTime = list[position].date
        holder.dateTv.text = dateTime.substring(0, 10) + " " + dateTime.substring(11, 19)
        holder.titleTv.text = list[position].title

        GlideApp.with(activity).load(list[position].image)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    holder.card.background = resource

                    holder.card.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(list[holder.adapterPosition].link)
                        activity.startActivity(intent)
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }
}