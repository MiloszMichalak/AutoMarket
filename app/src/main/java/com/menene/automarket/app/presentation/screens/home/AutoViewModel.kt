package com.menene.automarket.app.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menene.automarket.app.domain.use_case.GetAutosUseCase
import com.menene.automarket.app.domain.use_case.RefreshAutoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutoViewModel @Inject constructor(
    getAutosUseCase: GetAutosUseCase,
    refreshAutosList: RefreshAutoListUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            refreshAutosList.invoke()
        }
    }

    val autos = getAutosUseCase.invoke()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        ).onSubscription {
            refreshAutosList.invoke()
        }
}
