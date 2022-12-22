package com.gmail.bukin26.mg.data

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()
}

suspend fun <T> wrapResult(block: suspend () -> T) = try {
    Result.Success(block.invoke())
} catch (t: Throwable) {
    Result.Error(t)
}