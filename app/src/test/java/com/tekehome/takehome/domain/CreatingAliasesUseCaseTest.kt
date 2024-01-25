package com.tekehome.takehome.domain

import com.tekehome.takehome.domain.usecase.CreatingAliasesUseCase
import com.tekehome.takehome.util.MainDispatcherRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class CreatingAliasesUseCaseTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    companion object {
        private const val NU_URL =
            "https://nu.com.mx/?cq_plac=&cq_net=g&cq_pos=&cq_med=&cq_plt=gp&utm_source=google&utm_medium=cpc&utm_campaign=15501076442&utm_term=129518566045&utm_word=nubank&utm_content=650819243402&ad_position=&match_type=e&location=9133506&device=c&utm_keyword_id=kwd-306018933097&gad_source=1&gclid=Cj0KCQiA7aSsBhCiARIsALFvovyGKIG5Rbq6zFEJSR6HXtVo-BxXdUGK1ksc9QzIGj-i0aqpQjQQ6A4aApgXEALw_wcB"
        private const val ALIAS = "50741931"
    }

    @Inject
    lateinit var creatingAliasesUseCase: CreatingAliasesUseCase

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun `Post creating aliases Use Case`() = runTest {
        val creatingAliases = creatingAliasesUseCase(NU_URL)
        advanceUntilIdle()
        creatingAliases.collect { serviceAliases ->
            Assert.assertEquals(serviceAliases.alias, ALIAS)
            Assert.assertEquals(serviceAliases.self, NU_URL)
            Assert.assertTrue(serviceAliases.shortUrl.isNotEmpty())
        }
    }
}
