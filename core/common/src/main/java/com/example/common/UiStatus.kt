package com.example.common

sealed class UiStatus<T>(val data: T? = null, val message: String? = null) {
    class Loading<T> : UiStatus<T>()
    class Success<T>(data: T?) : UiStatus<T>(data)
    class Failure<T>(message: String?) : UiStatus<T>(message = message)
}