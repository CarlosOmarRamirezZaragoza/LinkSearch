package com.tekehome.takehome.domain.entity

import com.tekehome.takehome.di.EMPTY_URL

data class ServiceAliases(
    val alias: String,
    val self: String,
    val shortUrl: String = EMPTY_URL,
)
