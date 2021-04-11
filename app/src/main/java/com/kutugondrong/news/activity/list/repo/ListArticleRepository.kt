package com.kutugondrong.news.activity.list.repo

import com.kutugondrong.news.network.Services
import com.kutugondrong.news.utils.API_KEY
import com.kutugondrong.news.utils.PAGE_SIZE

class ListArticleRepository(private val services: Services){
    suspend fun getArticleNews(source: String, page: Int) =
        services.getArticleNews(API_KEY, PAGE_SIZE, source, page)
}