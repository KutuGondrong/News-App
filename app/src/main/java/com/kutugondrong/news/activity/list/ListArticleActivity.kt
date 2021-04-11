package com.kutugondrong.news.activity.list

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kutugondrong.news.R
import com.kutugondrong.news.activity.detail.DetailArticleActivity
import com.kutugondrong.news.activity.list.viewmodel.ListArticleViewModel
import com.kutugondrong.news.adapter.ArticleRVAdapter
import com.kutugondrong.news.adapter.ArticleRVAdapterListener
import com.kutugondrong.news.model.ArticleNews
import com.kutugondrong.news.model.SourceNews
import com.kutugondrong.news.network.ProgressStatus
import com.kutugondrong.news.utils.EXTRA_DATA
import kotlinx.android.synthetic.main.activity_list_article.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListArticleActivity : AppCompatActivity(), ArticleRVAdapterListener {

    private val listArticleViewModel: ListArticleViewModel by viewModel()
    private lateinit var articleRVAdapter : ArticleRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_article)
        initFirst()
    }

    private fun initFirst() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getSource().name
        articleRVAdapter = ArticleRVAdapter(this)
        rv_article.apply {
            layoutManager = LinearLayoutManager(this@ListArticleActivity)
            adapter = articleRVAdapter
        }
        rv_article.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    listArticleViewModel.getArticleNews(getSource())
                }
            }
        })
        listArticleViewModel.getArticleNews(getSource())
        listArticleViewModel.progressLiveData.observe(this, Observer {
            when (it) {
                is ProgressStatus.Loading -> displayLoadingData(it.load!!)
                is ProgressStatus.Success<*> -> displayData(it.data as ArticleNews.ArticleNewsResponse)
                is ProgressStatus.Error -> displayErrorData(it.errorMessage)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getSource() : SourceNews.SourceResponse {
        return intent.getParcelableExtra(EXTRA_DATA)!!
    }

    private fun displayLoadingData(load: Boolean) {
        if (load) {
            loadingPage.visibility = View.VISIBLE
        } else {
            rv_article.visibility = View.GONE
            shimmerLayout.visibility = View.VISIBLE
        }
    }

    private fun displayData(articleResponse: ArticleNews.ArticleNewsResponse) {
        articleRVAdapter.initData(articleResponse.articles)
        rv_article.visibility = View.VISIBLE
        shimmerLayout.visibility = View.GONE
        loadingPage.visibility = View.GONE
    }

    private fun displayErrorData(errorMessage: String) {
        rv_article.visibility = View.VISIBLE
        shimmerLayout.visibility = View.GONE
        loadingPage.visibility = View.GONE
    }


    override fun onCLickItemArticleRVAdapter(item: ArticleNews.ArticleResponse) {
        val intent = Intent(this, DetailArticleActivity::class.java)
        intent.putExtra(EXTRA_DATA, item)
        startActivity(intent)
    }

}