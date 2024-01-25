package com.tekehome.takehome.data.datasource.remote.model.response

import com.squareup.moshi.Json

data class CreatingAliasesDto(
    val alias: String,
    @field:Json(name = "_links") val links: CreatingAliasesLink,
)

data class CreatingAliasesLink(
    val self: String,
    val short: String,
)
