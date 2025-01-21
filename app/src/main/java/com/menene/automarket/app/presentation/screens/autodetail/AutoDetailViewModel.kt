package com.menene.automarket.app.presentation.screens.autodetail

import androidx.lifecycle.ViewModel
import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.domain.use_case.GetAutoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AutoDetailViewModel @Inject constructor(
    private val getAutoUseCase: GetAutoUseCase
) : ViewModel() {
    fun getAuto(autoId: Int): Flow<Auto> {
        return getAutoUseCase.invoke(autoId)
    }
}
