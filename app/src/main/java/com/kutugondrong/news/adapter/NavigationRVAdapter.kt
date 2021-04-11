package com.kutugondrong.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kutugondrong.news.R
import com.kutugondrong.news.model.CategoryNews
import kotlinx.android.synthetic.main.item_nav_drawer.view.*

class NavigationRVAdapter(private var items: ArrayList<CategoryNews>,
                          private val listener: NavigationRVListener) :
    RecyclerView.Adapter<NavigationRVAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context
    private var currentPos = 0

    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem = LayoutInflater.from(parent.context).inflate(R.layout.item_nav_drawer, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {
        if (position == currentPos) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_200))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        }

        holder.itemView.navigation_title.text = items[position].title
        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                onClickItemNavigationRV(position)
            }

        })
    }

    private fun onClickItemNavigationRV(position: Int) {
        currentPos = position
        listener?.onCLickItemNavigationRV(currentPos)
        notifyDataSetChanged()
    }


}

interface NavigationRVListener {
    fun onCLickItemNavigationRV(position: Int)
}