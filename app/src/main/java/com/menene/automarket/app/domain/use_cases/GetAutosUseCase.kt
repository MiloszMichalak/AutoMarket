package com.menene.automarket.app.domain.use_cases

import com.menene.automarket.app.domain.model.AutoFilter
import com.menene.automarket.app.domain.repository.AutoRepository
import javax.inject.Inject

class GetAutosUseCase @Inject constructor(
    private val autoRepository: AutoRepository
) {
    suspend operator fun invoke(filter: AutoFilter) = autoRepository.getAutos(filter)
}