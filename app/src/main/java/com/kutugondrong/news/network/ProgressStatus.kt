package com.kutugondrong.news.network

sealed class ProgressStatus {
    class Loading(val load: Boolean?) : ProgressStatus()
    class Success<T>(val data: T) : ProgressStatus()
    class Error(val errorMessage: String) : ProgressStatus()
}