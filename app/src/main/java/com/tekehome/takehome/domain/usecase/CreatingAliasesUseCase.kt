package com.tekehome.takehome.domain.usecase

import com.tekehome.takehome.domain.entity.ServiceAliases
import com.tekehome.takehome.domain.mapper.CreatingAliasesMapper
import com.tekehome.takehome.domain.repository.TakeHomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreatingAliasesUseCase @Inject constructor(private val repository: TakeHomeRepository) {
    operator fun invoke(url: String): Flow<ServiceAliases> =
        CreatingAliasesMapper.fromAliasesDataToAliasesDomain(repository.postCreatingAliases(url))
}
