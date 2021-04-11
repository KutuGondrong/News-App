package com.kutugondrong.news.di

import com.kutugondrong.news.network.Services
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(Services::class.java) }
}