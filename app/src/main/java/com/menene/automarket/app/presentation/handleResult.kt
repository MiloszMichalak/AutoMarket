package com.menene.automarket.app.presentation

import com.menene.automarket.app.presentation.model.UiState
import com.menene.automarket.core.util.Result

fun <T> handleResult(
    result: Result<T, String>
): UiState<T> {
    return when (result) {
        is Result.Success -> UiState.Success(result.data)
        is Result.Error -> UiState.Error(result.error)
    }
}