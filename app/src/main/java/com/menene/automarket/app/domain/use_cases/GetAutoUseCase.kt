package com.menene.automarket.app.domain.use_cases

import com.menene.automarket.app.domain.repository.AutoRepository
import javax.inject.Inject

class GetAutoUseCase @Inject constructor(
    private val autoRepository: AutoRepository
) {
    suspend operator fun invoke(id: Int) = autoRepository.getAuto(id)
}