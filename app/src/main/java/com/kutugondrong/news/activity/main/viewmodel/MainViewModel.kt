package com.kutugondrong.news.activity.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kutugondrong.news.activity.main.repo.MainRepository
import com.kutugondrong.news.model.CategoryNews
import com.kutugondrong.news.network.ProgressStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    var positionActive = 0

    val categories = arrayListOf(
        CategoryNews("general", "General"),
        CategoryNews("business", "Business"),
        CategoryNews("entertainment", "Entertainment"),
        CategoryNews("health", "Health"),
        CategoryNews("science", "Science"),
        CategoryNews("sports", "Sports"),
        CategoryNews("technology", "Technology")
    )

    val progressLiveSource = MutableLiveData<ProgressStatus>()


    fun getSourceNews () {
        viewModelScope.launch {
            progressLiveSource.postValue(ProgressStatus.Loading(null))
            withContext(Dispatchers.IO) {
                try {
                    val result = repository.getSourceNews(categories[positionActive].id)
                    progressLiveSource.postValue(ProgressStatus.Success(result))
                } catch (throwable: Throwable) {
                    progressLiveSource.postValue(ProgressStatus.Error(throwable.message.toString()))
                }
            }
        }
    }


}