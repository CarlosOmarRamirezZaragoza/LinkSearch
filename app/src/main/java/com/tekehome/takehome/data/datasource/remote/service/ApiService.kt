package com.tekehome.takehome.data.datasource.remote.service

import com.tekehome.takehome.data.datasource.remote.model.request.UrlData
import com.tekehome.takehome.data.datasource.remote.model.response.CreatingAliasesDto
import com.tekehome.takehome.data.datasource.remote.model.response.UrlDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val ALIAS = "api/alias/"
    }

    @POST(ALIAS)
    suspend fun postCreatingAliases(@Body aliases: UrlData): CreatingAliasesDto

    @GET("$ALIAS{id}")
    suspend fun getUrlAliases(@Path("id") id: String): UrlDto
}
