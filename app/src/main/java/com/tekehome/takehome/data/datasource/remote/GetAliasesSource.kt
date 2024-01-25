package com.tekehome.takehome.data.datasource.remote

import kotlinx.coroutines.flow.Flow

interface GetAliasesSource {
    fun getAliasesSource(id: String): Flow<String>
}
