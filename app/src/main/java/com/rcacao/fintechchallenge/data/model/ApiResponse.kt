package com.rcacao.fintechchallenge.data.model

sealed class ApiResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : ApiResponse<T>()
    data class Failure(val exception: Exception) : ApiResponse<Nothing>()
}