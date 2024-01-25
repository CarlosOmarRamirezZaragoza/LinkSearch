package com.tekehome.takehome.data.datasource.remote

import com.tekehome.takehome.data.datasource.remote.mapper.TakeHomeMapperData
import com.tekehome.takehome.data.datasource.remote.service.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAliasesDataSource @Inject constructor(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher,
) : GetAliasesSource {
    override fun getAliasesSource(id: String): Flow<String> = flow {
        val response = apiService.getUrlAliases(id)
        emit(TakeHomeMapperData.fromJsonToUrlDto(response))
    }.flowOn(dispatcher)
}
