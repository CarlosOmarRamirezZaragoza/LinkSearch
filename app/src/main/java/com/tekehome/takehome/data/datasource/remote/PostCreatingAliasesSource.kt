package com.tekehome.takehome.data.datasource.remote

import com.tekehome.takehome.data.datasource.remote.model.request.UrlData
import com.tekehome.takehome.data.datasource.remote.model.response.ServiceAliasesData
import kotlinx.coroutines.flow.Flow

interface PostCreatingAliasesSource {
    fun postCreatingAliasesSource(urlData: UrlData): Flow<ServiceAliasesData>
}
