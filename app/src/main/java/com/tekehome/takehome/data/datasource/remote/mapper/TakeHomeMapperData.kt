package com.tekehome.takehome.data.datasource.remote.mapper

import com.tekehome.takehome.data.datasource.remote.model.request.UrlData
import com.tekehome.takehome.data.datasource.remote.model.response.CreatingAliasesDto
import com.tekehome.takehome.data.datasource.remote.model.response.ServiceAliasesData
import com.tekehome.takehome.data.datasource.remote.model.response.UrlDto

object TakeHomeMapperData {
    const val URL = "url"
    fun fromAliasesDtoToAliasesData(aliases: CreatingAliasesDto): ServiceAliasesData {
        return ServiceAliasesData(
            alias = aliases.alias,
            self = aliases.links.self,
            short = aliases.links.short,
        )
    }

    fun fromUrlDomainToUrlData(url: String): UrlData {
        return UrlData(url = url)
    }

    fun fromJsonToUrlDto(urlDto: UrlDto): String {
        return urlDto.url
    }
}
