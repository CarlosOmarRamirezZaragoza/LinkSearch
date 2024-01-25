package com.tekehome.takehome.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TakeHomeDbEntity(
    @PrimaryKey
    val id: Int,
    var alias: String,
    var self: String,
    var shortUrl: String,
)
