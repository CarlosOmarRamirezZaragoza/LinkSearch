package com.tekehome.takehome.domain.repository

import com.tekehome.takehome.domain.entity.TakeHomeDataBase

interface TakeHomeDataBaseRepository {
    suspend fun add(takeHomeAdd: TakeHomeDataBase)
    suspend fun update(takeHomeAdd: TakeHomeDataBase)
    suspend fun delete(takeHomeAdd: TakeHomeDataBase)
}
