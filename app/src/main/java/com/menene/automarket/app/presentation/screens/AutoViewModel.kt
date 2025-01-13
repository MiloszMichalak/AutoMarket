package com.menene.automarket.app.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.domain.model.AutoFilter
import com.menene.automarket.app.domain.use_cases.GetAutoUseCase
import com.menene.automarket.app.domain.use_cases.GetAutosUseCase
import com.menene.automarket.app.presentation.model.UiState
import com.menene.automarket.core.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutoViewModel @Inject constructor(
    private val getAutosUseCase: GetAutosUseCase,
    private val getAutoUseCase: GetAutoUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Auto>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _autoUiState = MutableStateFlow<UiState<Auto>>(UiState.Loading)
    val autoUiState = _autoUiState.asStateFlow()

    init {
        getAutos()
    }

    fun getAutos(filter: AutoFilter = AutoFilter()) {
        viewModelScope.launch {
            val result = getAutosUseCase.invoke(filter)
            _uiState.value = handleResult(result)
        }
    }

    fun getAuto(id: Int){
        viewModelScope.launch {
            val result = getAutoUseCase.invoke(id)
            _autoUiState.value = handleResult(result)
        }
    }

    fun clearAutoUiState(){
        _autoUiState.value = UiState.Loading
    }
}

private fun <T> handleResult(
    result: Result<T, String>
): UiState<T> {
    return when (result) {
        is Result.Success -> UiState.Success(result.data)
        is Result.Error -> UiState.Error(result.error)
    }
}