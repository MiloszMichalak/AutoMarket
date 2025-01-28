package com.menene.automarket.app.domain.use_case

import com.menene.automarket.app.domain.repository.AutoRepository
import com.menene.automarket.app.util.Result

class RefreshAutoListUseCase(
    private val autoRepository: AutoRepository
) {
    suspend operator fun invoke(): Result<Unit, String> {
        return autoRepository.refresh()
    }
}