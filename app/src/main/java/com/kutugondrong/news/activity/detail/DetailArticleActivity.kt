package com.kutugondrong.news.activity.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.kutugondrong.news.R
import com.kutugondrong.news.model.ArticleNews
import com.kutugondrong.news.utils.EXTRA_DATA
import kotlinx.android.synthetic.main.activity_detail_article.*

class DetailArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_article)
        initFirst()
    }

    private fun initFirst() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getSource().author
        Glide.with(this).load(getSource().urlToImage).into(imgArticle)
        txtTitle.text = getSource().title
        txtDescription.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(getSource().content, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(getSource().content)
        }
    }

    private fun getSource() : ArticleNews.ArticleResponse {
        return intent.getParcelableExtra(EXTRA_DATA)!!
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
}