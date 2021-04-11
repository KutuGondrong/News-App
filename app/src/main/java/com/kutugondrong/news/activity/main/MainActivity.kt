package com.kutugondrong.news.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kutugondrong.news.R
import com.kutugondrong.news.activity.list.ListArticleActivity
import com.kutugondrong.news.activity.main.viewmodel.MainViewModel
import com.kutugondrong.news.adapter.NavigationRVAdapter
import com.kutugondrong.news.adapter.NavigationRVListener
import com.kutugondrong.news.adapter.SourceRVAdapter
import com.kutugondrong.news.adapter.SourceRVAdapterListener
import com.kutugondrong.news.model.SourceNews
import com.kutugondrong.news.network.ProgressStatus
import com.kutugondrong.news.utils.EXTRA_DATA
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.Serializable

class MainActivity : AppCompatActivity(), NavigationRVListener, SourceRVAdapterListener {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var mToggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
        mainViewModel.getSourceNews()
        mainViewModel.progressLiveSource.observe(this, Observer {
            when (it) {
                is ProgressStatus.Loading -> displayLoadingSource()
                is ProgressStatus.Success<*> -> displayDataSource(it.data as SourceNews.SourceNewsResponse)
                is ProgressStatus.Error -> displayErrorSource(it.errorMessage)
            }
        })

    }

    private fun setupNavigation() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mToggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(mToggle)
        mToggle.syncState()
        rv_navigation.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = NavigationRVAdapter(mainViewModel.categories, this@MainActivity)
        }
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return mToggle.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCLickItemNavigationRV(position: Int) {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        mainViewModel.positionActive = position
        mainViewModel.getSourceNews()
    }

    private fun displayLoadingSource() {
        rv_source.visibility = View.GONE
        shimmerLayout.visibility = View.VISIBLE
    }

    private fun displayDataSource(sourceNewsResponse: SourceNews.SourceNewsResponse) {
        rv_source.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = SourceRVAdapter(sourceNewsResponse.sources, this@MainActivity)
        }
        rv_source.visibility = View.VISIBLE
        shimmerLayout.visibility = View.GONE
    }

    private fun displayErrorSource(errorMessage: String) {
        rv_source.visibility = View.VISIBLE
        shimmerLayout.visibility = View.GONE
    }

    override fun onCLickItemNavigationRV(item: SourceNews.SourceResponse) {
        val intent = Intent(this, ListArticleActivity::class.java)
        intent.putExtra(EXTRA_DATA, item)
        startActivity(intent)
    }

}