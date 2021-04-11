package com.kutugondrong.news.network

import com.kutugondrong.news.model.ArticleNews
import com.kutugondrong.news.model.SourceNews
import retrofit2.http.GET
import retrofit2.http.Query


interface Services {
    @GET("v2/sources")
    suspend fun getSourceNews(@Query("apiKey") apiKey: String,
                              @Query("category") category: String)
            : SourceNews.SourceNewsResponse

    @GET("v2/everything")
    suspend fun getArticleNews(@Query("apiKey") apiKey: String,
                       @Query("pageSize") pageSize: Int,
                       @Query("sources") sources: String,
                       @Query("page") page: Int): ArticleNews.ArticleNewsResponse
}