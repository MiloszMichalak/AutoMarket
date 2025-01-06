package com.menene.automarket.presentation

import androidx.lifecycle.ViewModel
import com.menene.automarket.domain.model.Auto
import com.menene.automarket.domain.repository.AutoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AutoViewModel @Inject constructor(
    private val autoRepository: AutoRepository
): ViewModel(){
    private val _autos = MutableStateFlow(emptyList<Auto>())
    val autos = _autos

    suspend fun getAutos(
        brand: String? = null,
        model: String? = null,
        minYear: Short? = null,
        maxYear: Short? = null,
        minPrice: UInt? = null,
        maxPrice: UInt? = null
    ){
        _autos.value = autoRepository.getAutos()
    }
}