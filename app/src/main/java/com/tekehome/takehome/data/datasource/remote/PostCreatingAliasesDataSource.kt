package com.tekehome.takehome.data.datasource.remote

import com.squareup.moshi.JsonAdapter
import com.tekehome.takehome.data.datasource.remote.mapper.TakeHomeMapperData
import com.tekehome.takehome.data.datasource.remote.model.request.UrlData
import com.tekehome.takehome.data.datasource.remote.model.response.ServiceAliasesData
import com.tekehome.takehome.data.datasource.remote.service.ApiService
import com.tekehome.takehome.di.EMPTY_URL
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostCreatingAliasesDataSource @Inject constructor(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher,
    private val moshi: JsonAdapter<UrlData>,
) : PostCreatingAliasesSource {
    override fun postCreatingAliasesSource(urlData: UrlData): Flow<ServiceAliasesData> = flow {
        val response = apiService.postCreatingAliases(
            moshi.fromJson(moshi.toJson(urlData)) ?: UrlData(EMPTY_URL),
        )
        emit(TakeHomeMapperData.fromAliasesDtoToAliasesData(response))
    }.flowOn(dispatcher)
}
