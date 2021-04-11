package com.kutugondrong.news.di

import com.kutugondrong.news.activity.list.viewmodel.ListArticleViewModel
import com.kutugondrong.news.activity.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { ListArticleViewModel(get()) }
}
