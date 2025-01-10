package com.menene.automarket.app.data.api

import com.menene.automarket.core.util.Result
import okio.IOException
import retrofit2.HttpException
import java.net.HttpRetryException

inline fun <D> safeApiCall(apiCall: () -> D): Result<D, String> {
    return try {
        val response = apiCall()
        Result.Success(response)
    } catch (throwable: Throwable) {
        when (throwable) {
            is HttpRetryException -> Result.Error("Retry error")
            is IOException -> Result.Error("Network error")
            is HttpException -> {
                val code = throwable.code()
                val errorBody = throwable.response()?.errorBody()?.string() ?: "Unknown error"
                Result.Error("Error $code: $errorBody")
            }
            else -> Result.Error("Unknown s")
        }
    }
}