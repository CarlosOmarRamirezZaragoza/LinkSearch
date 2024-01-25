package com.tekehome.takehome.di

import android.app.Application
import android.content.Context
import com.tekehome.takehome.data.datasource.remote.GetAliasesDataSource
import com.tekehome.takehome.data.datasource.remote.GetAliasesSource
import com.tekehome.takehome.data.datasource.remote.PostCreatingAliasesDataSource
import com.tekehome.takehome.data.datasource.remote.PostCreatingAliasesSource
import com.tekehome.takehome.data.repository.TakeHomeDataDataBaseRepository
import com.tekehome.takehome.data.repository.TakeHomeDataRepository
import com.tekehome.takehome.domain.repository.TakeHomeDataBaseRepository
import com.tekehome.takehome.domain.repository.TakeHomeRepository
import com.tekehome.takehome.presentation.resources.TakeHomeResources
import com.tekehome.takehome.presentation.resources.TakeHomeResourcesManager
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [TakeHomeModule::class],
)
interface TakeHomeModuleTest {

    @Binds
    fun bindPostAliasesTasks(
        postCreatingAliasesDataSource: PostCreatingAliasesDataSource,
    ): PostCreatingAliasesSource

    @Binds
    fun bindGetAliasesTasks(
        getAliasesDataSource: GetAliasesDataSource,
    ): GetAliasesSource

    @Binds
    fun bindTakeHomeRepository(
        takeHomeDataRepository: TakeHomeDataRepository,
    ): TakeHomeRepository

    @Binds
    fun bindTakeHomeDataBaseRepository(
        takeHomeDataDataBaseRepository: TakeHomeDataDataBaseRepository,
    ): TakeHomeDataBaseRepository

    @Binds
    fun bindTakeHomeResourcesManager(
        takeHomeResourcesManager: TakeHomeResourcesManager,
    ): TakeHomeResources
}
