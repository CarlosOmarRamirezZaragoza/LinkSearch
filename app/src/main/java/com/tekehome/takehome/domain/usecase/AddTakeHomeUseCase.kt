package com.tekehome.takehome.domain.usecase

import com.tekehome.takehome.data.repository.TakeHomeDataDataBaseRepository
import com.tekehome.takehome.domain.entity.TakeHomeDataBase
import javax.inject.Inject

class AddTakeHomeUseCase @Inject constructor(private val repository: TakeHomeDataDataBaseRepository) {
    suspend operator fun invoke(takeHomeDataBase: TakeHomeDataBase) {
        repository.add(takeHomeDataBase)
    }
}
