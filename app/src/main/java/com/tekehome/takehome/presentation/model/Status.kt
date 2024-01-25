package com.tekehome.takehome.presentation.model

import com.tekehome.takehome.domain.entity.ServiceAliases

sealed class Status {
    data class Failure(val error: String) : Status()
    data class Success(val items: List<ServiceAliases>) : Status()
}
