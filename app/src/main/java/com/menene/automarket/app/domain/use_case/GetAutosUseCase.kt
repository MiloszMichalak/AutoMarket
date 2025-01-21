package com.menene.automarket.app.domain.use_case

import com.menene.automarket.app.domain.model.AutoFilter
import com.menene.automarket.app.domain.repository.AutoRepository
import javax.inject.Inject

class GetAutosUseCase @Inject constructor(
    private val autoRepository: AutoRepository
) {
    operator fun invoke(filter: AutoFilter? = null) = autoRepository.getAutos(filter)
}