package com.tekehome.takehome.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tekehome.takehome.data.datasource.local.model.TakeHomeDbEntity
import com.tekehome.takehome.data.datasource.local.service.TakeHomeDao

@Database(entities = [TakeHomeDbEntity::class], version = 1)
abstract class TakeHomeDataBase : RoomDatabase() {
    abstract fun takeHomeDao(): TakeHomeDao
}
