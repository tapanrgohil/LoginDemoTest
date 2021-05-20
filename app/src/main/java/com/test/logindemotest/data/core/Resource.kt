package com.test.logindemotest.data.core


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
data class Resource<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null,
    val throwable: Throwable? = null,
) {

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(
            message: String?,
            data: T? = null,
            throwable: Throwable? = null,
        ): Resource<T> {
            return Resource(Status.ERROR, data, message, throwable)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}