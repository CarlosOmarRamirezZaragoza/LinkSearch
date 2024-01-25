package com.tekehome.takehome.data.repository

import com.tekehome.takehome.data.datasource.local.model.TakeHomeDbEntity
import com.tekehome.takehome.data.datasource.local.service.TakeHomeDao
import com.tekehome.takehome.domain.entity.TakeHomeDataBase
import com.tekehome.takehome.domain.repository.TakeHomeDataBaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TakeHomeDataDataBaseRepository @Inject constructor(
    private val takeHomeDao: TakeHomeDao,
) : TakeHomeDataBaseRepository {
    val getAllTakeHomeItems: Flow<List<TakeHomeDataBase>> =
        takeHomeDao.getTasks().map { items ->
            items.map {
                TakeHomeDataBase(it.id, it.alias, it.self, it.shortUrl)
            }
        }

    override suspend fun add(takeHomeAdd: TakeHomeDataBase) {
        takeHomeDao.addTakeHome(takeHomeAdd.toData())
    }

    override suspend fun update(takeHomeAdd: TakeHomeDataBase) {
        takeHomeDao.updateTakeHome(takeHomeAdd.toData())
    }

    override suspend fun delete(takeHomeAdd: TakeHomeDataBase) {
        takeHomeDao.deleteTakeHome(takeHomeAdd.toData())
    }

    private fun TakeHomeDataBase.toData(): TakeHomeDbEntity {
        return TakeHomeDbEntity(this.id, this.alias, this.self, this.shortUrl)
    }
}
