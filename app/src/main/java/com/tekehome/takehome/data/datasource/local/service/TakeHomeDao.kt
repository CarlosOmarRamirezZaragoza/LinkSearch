package com.tekehome.takehome.data.datasource.local.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tekehome.takehome.data.datasource.local.model.TakeHomeDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TakeHomeDao {
    @Query("SELECT * from TakeHomeDbEntity")
    fun getTasks(): Flow<List<TakeHomeDbEntity>>

    @Insert
    suspend fun addTakeHome(item: TakeHomeDbEntity)

    @Update
    suspend fun updateTakeHome(item: TakeHomeDbEntity)

    @Delete
    suspend fun deleteTakeHome(item: TakeHomeDbEntity)
}
