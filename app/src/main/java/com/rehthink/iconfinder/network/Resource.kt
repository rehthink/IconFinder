package com.rehthink.iconfinder.network

sealed class Resource<out Result> {

    object isLoading : Resource<Nothing>()
    data class Success<Result>(val result: Result) : Resource<Result>()
    data class Error(val errorMessage: String, val errorCode: Int): Resource<Nothing>()
}