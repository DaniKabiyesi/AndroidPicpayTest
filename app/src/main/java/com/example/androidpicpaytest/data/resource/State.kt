package com.example.androidpicpaytest.data.resource

data class State<out T>(val status: Status, val data: T?, val message: String?){
    companion object{
        fun <T> success(data: T): State<T> =
            State(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String?): State<T> =
            State(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): State<T> =
            State(status = Status.LOADING, data = data, message = null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
