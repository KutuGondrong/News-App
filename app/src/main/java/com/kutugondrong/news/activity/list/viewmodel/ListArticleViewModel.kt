package com.kutugondrong.news.activity.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kutugondrong.news.activity.list.repo.ListArticleRepository
import com.kutugondrong.news.model.SourceNews
import com.kutugondrong.news.network.ProgressStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListArticleViewModel(private val repository: ListArticleRepository) : ViewModel() {

    private var page = 1

    val progressLiveData = MutableLiveData<ProgressStatus>()

    fun getArticleNews (source: SourceNews.SourceResponse) {
        if (page > 0) {
            viewModelScope.launch {
                progressLiveData.postValue(ProgressStatus.Loading(page != 1))
                withContext(Dispatchers.IO) {
                    try {
                        val result = repository.getArticleNews(source.id!!, page)
                        progressLiveData.postValue(ProgressStatus.Success(result))
                        if (result.articles.size == 10) {
                            page ++
                        } else {
                            page = 0
                        }
                    } catch (throwable: Throwable) {
                        progressLiveData.postValue(ProgressStatus.Error(throwable.message.toString()))
                    }
                }
            }
        }
    }


}