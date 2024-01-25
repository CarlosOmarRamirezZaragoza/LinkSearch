package com.tekehome.takehome.domain.usecase

import com.tekehome.takehome.domain.repository.TakeHomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUrlAliasesUseCase @Inject constructor(private val repository: TakeHomeRepository) {
    operator fun invoke(id: String): Flow<String> = repository.getUrlAliases(id)
}
