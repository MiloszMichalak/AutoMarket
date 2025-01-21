package com.menene.automarket.app.domain.use_case

import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.domain.repository.AutoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAutoUseCase @Inject constructor(
    private val autoRepository: AutoRepository
) {
    operator fun invoke(id: Int): Flow<Auto> = autoRepository.getAuto(id)
}