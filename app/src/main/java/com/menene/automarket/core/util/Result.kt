package com.menene.automarket.core.util

sealed interface Result<out D, out E>{
    class Success<out D>(val data: D): Result<D, Nothing>
    class Error<out E>(val error: E): Result<Nothing, E>
}