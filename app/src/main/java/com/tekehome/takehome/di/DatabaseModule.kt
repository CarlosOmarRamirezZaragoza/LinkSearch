package com.tekehome.takehome.di

import android.content.Context
import androidx.room.Room
import com.tekehome.takehome.data.datasource.local.TakeHomeDataBase
import com.tekehome.takehome.data.datasource.local.service.TakeHomeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    companion object {
        const val DATABASE_NAME = "TakeHomeDatabase"
    }

    @Provides
    fun provideTakeHomeDao(takeHomeDataBase: TakeHomeDataBase): TakeHomeDao {
        return takeHomeDataBase.takeHomeDao()
    }

    @Provides
    @Singleton
    fun provideTakeHomeDatabase(@ApplicationContext appContext: Context): TakeHomeDataBase {
        return Room.databaseBuilder(appContext, TakeHomeDataBase::class.java, DATABASE_NAME)
            .build()
    }
}
