package com.menene.automarket.app.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.domain.model.AutoFilter
import com.menene.automarket.app.domain.use_cases.GetAutosUseCase
import com.menene.automarket.app.presentation.handleResult
import com.menene.automarket.app.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutoViewModel @Inject constructor(
    private val getAutosUseCase: GetAutosUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Auto>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getAutos()
    }

    private fun getAutos(filter: AutoFilter = AutoFilter()) {
        viewModelScope.launch {
            val result = getAutosUseCase.invoke(filter)
            _uiState.value = handleResult(result)
        }
    }

//    fun getAuto(id: Int){
//        viewModelScope.launch {
//            when (val result = autoRepository.getAuto(id)) {
//                is Result.Success -> _uiState.value = UiState.Success(result.data)
//                is Result.Error -> _uiState.value = UiState.Error(result.error)
//            }
//        }
//    }
}