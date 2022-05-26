package com.kec.newsfresh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter( private val listener: onItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {
    private val news : ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onClicked(news[viewHolder.adapterPosition])
        }
        return viewHolder
    }


    override fun onBindViewHolder(holder:NewsViewHolder, position: Int) {
        val currentItem = news[position]
        holder.title.text = currentItem.title
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
        holder.author.text = currentItem.author

    }

    override fun getItemCount(): Int {
       return news.size
    }

    fun updateNews(updatedNews : ArrayList<News>){
        news.clear()
        news.addAll(updatedNews)

        notifyDataSetChanged()

    }
}

class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val image : ImageView = itemView.findViewById(R.id.image)
    val title : TextView = itemView.findViewById(R.id.title)
    val author : TextView = itemView.findViewById(R.id.author)

}

interface onItemClicked
{
    fun onClicked(item : News)
}