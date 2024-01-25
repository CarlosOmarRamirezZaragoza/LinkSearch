package com.tekehome.takehome.domain.repository

import com.tekehome.takehome.data.datasource.remote.model.response.ServiceAliasesData
import kotlinx.coroutines.flow.Flow

interface TakeHomeRepository {
    fun postCreatingAliases(url: String): Flow<ServiceAliasesData>
    fun getUrlAliases(id: String): Flow<String>
}
