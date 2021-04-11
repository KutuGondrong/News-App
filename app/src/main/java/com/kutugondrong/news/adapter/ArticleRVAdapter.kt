package com.kutugondrong.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kutugondrong.news.R
import com.kutugondrong.news.model.ArticleNews
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleRVAdapter(private val listener: ArticleRVAdapterListener) :
    RecyclerView.Adapter<ArticleRVAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context
    private var items: ArrayList<ArticleNews.ArticleResponse> = ArrayList()

    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {
        Glide.with(context).load(items[position].urlToImage).into(holder.itemView.imgArticle)
        holder.itemView.txtTitle.text = items[position].title
        holder.itemView.txtDescription.text = items[position].description
        holder.itemView.setOnClickListener {
            listener?.onCLickItemArticleRVAdapter(items[position])
        }
    }

    fun initData(_items: ArrayList<ArticleNews.ArticleResponse>) {
        items.addAll(_items)
        notifyDataSetChanged()
    }

}

interface ArticleRVAdapterListener {
    fun onCLickItemArticleRVAdapter(item: ArticleNews.ArticleResponse)
}