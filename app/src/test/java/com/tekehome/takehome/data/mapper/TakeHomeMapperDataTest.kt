package com.tekehome.takehome.data.mapper

import com.tekehome.takehome.data.datasource.remote.mapper.TakeHomeMapperData
import com.tekehome.takehome.data.datasource.remote.model.request.UrlData
import com.tekehome.takehome.data.datasource.remote.model.response.CreatingAliasesDto
import com.tekehome.takehome.data.datasource.remote.model.response.CreatingAliasesLink
import com.tekehome.takehome.data.datasource.remote.model.response.ServiceAliasesData
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class TakeHomeMapperDataTest {

    @Test
    fun `Given Aliases Dto mapper to Aliases Data`() {
        val aliasesDto = CreatingAliasesDto(
            alias = "12345",
            links = CreatingAliasesLink(
                "Nu_bank",
                "Nu",
            ),
        )
        val aliasesData = TakeHomeMapperData.fromAliasesDtoToAliasesData(aliasesDto)
        assertEquals(
            ServiceAliasesData(
                "12345",
                "Nu_bank",
                "Nu",
            ),
            aliasesData,
        )
    }

    @Test
    fun `Given Url Domain mapper to Url Data`() {
        val aliasesDto =
            "https://nu.com.mx/?cq_plac=&cq_net=g&cq_pos=&cq_med=&cq_plt=gp&utm_source=google&utm_medium=cpc&utm_campaign=15501076442&utm_term=129518566045&utm_word=nubank&utm_content=650819243402&ad_position=&match_type=e&location=9133506&device=c&utm_keyword_id=kwd-306018933097&gad_source=1&gclid=Cj0KCQiA7aSsBhCiARIsALFvovyGKIG5Rbq6zFEJSR6HXtVo-BxXdUGK1ksc9QzIGj-i0aqpQjQQ6A4aApgXEALw_wcB"
        val aliasesData = TakeHomeMapperData.fromUrlDomainToUrlData(aliasesDto)
        assertEquals(UrlData(aliasesDto), aliasesData)
    }
}
