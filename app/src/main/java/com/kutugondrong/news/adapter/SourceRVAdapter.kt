package com.kutugondrong.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kutugondrong.news.R
import com.kutugondrong.news.model.SourceNews
import kotlinx.android.synthetic.main.item_source.view.*

class SourceRVAdapter(private var items: ArrayList<SourceNews.SourceResponse>,
                      private val listener: SourceRVAdapterListener) :
    RecyclerView.Adapter<SourceRVAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context

    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem = LayoutInflater.from(parent.context).inflate(R.layout.item_source, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {
        holder.itemView.txtTitle.text = items[position].name
        holder.itemView.txtDescription.text = items[position].description
        holder.itemView.setOnClickListener {
            listener.onCLickItemNavigationRV(items[position])
        }
    }

}

interface SourceRVAdapterListener {
    fun onCLickItemNavigationRV(item: SourceNews.SourceResponse)
}