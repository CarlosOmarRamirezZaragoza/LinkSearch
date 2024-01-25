package com.tekehome.takehome.domain.entity

import com.tekehome.takehome.di.EMPTY_URL

data class TakeHomeDataBase(
    val id: Int = System.currentTimeMillis().hashCode(),
    var alias: String = EMPTY_URL,
    var self: String,
    var shortUrl: String = EMPTY_URL,
)
