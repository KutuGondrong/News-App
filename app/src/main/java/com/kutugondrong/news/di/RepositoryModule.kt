package com.kutugondrong.news.di

import com.kutugondrong.news.activity.list.repo.ListArticleRepository
import com.kutugondrong.news.activity.main.repo.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MainRepository(get()) }
    single { ListArticleRepository(get()) }
}