package com.kutugondrong.news.activity.main.repo

import com.kutugondrong.news.network.Services
import com.kutugondrong.news.utils.API_KEY

class MainRepository(private val services: Services){
    suspend fun getSourceNews(category: String) = services.getSourceNews(API_KEY, category)
}