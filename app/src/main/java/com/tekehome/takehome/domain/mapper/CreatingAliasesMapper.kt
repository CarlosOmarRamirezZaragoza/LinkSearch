package com.tekehome.takehome.domain.mapper

import com.tekehome.takehome.data.datasource.remote.model.response.ServiceAliasesData
import com.tekehome.takehome.domain.entity.ServiceAliases
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object CreatingAliasesMapper {
    fun fromAliasesDataToAliasesDomain(aliases: Flow<ServiceAliasesData>): Flow<ServiceAliases> {
        return aliases.map {
            ServiceAliases(
                alias = it.alias,
                self = it.self,
                shortUrl = it.short,
            )
        }
    }
}
