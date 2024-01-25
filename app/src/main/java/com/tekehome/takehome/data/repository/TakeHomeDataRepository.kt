package com.tekehome.takehome.data.repository

import com.tekehome.takehome.data.datasource.remote.GetAliasesSource
import com.tekehome.takehome.data.datasource.remote.PostCreatingAliasesSource
import com.tekehome.takehome.data.datasource.remote.mapper.TakeHomeMapperData.fromUrlDomainToUrlData
import com.tekehome.takehome.domain.repository.TakeHomeRepository
import javax.inject.Inject

class TakeHomeDataRepository @Inject constructor(
    private val postCreatingAliasesSource: PostCreatingAliasesSource,
    private val getAliasesSource: GetAliasesSource,
) : TakeHomeRepository {

    override fun postCreatingAliases(url: String) =
        postCreatingAliasesSource.postCreatingAliasesSource(fromUrlDomainToUrlData(url))

    override fun getUrlAliases(id: String) = getAliasesSource.getAliasesSource(id)
}
