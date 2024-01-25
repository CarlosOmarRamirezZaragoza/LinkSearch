package com.tekehome.takehome.domain.usecase

import com.tekehome.takehome.data.repository.TakeHomeDataDataBaseRepository
import com.tekehome.takehome.domain.entity.TakeHomeDataBase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTakeHomeUseCase @Inject constructor(private val repository: TakeHomeDataDataBaseRepository) {
    operator fun invoke(): Flow<List<TakeHomeDataBase>> = repository.getAllTakeHomeItems
}
