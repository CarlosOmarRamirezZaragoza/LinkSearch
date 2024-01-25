package com.tekehome.takehome.data.repository

import com.tekehome.takehome.data.datasource.remote.GetAliasesDataSource
import com.tekehome.takehome.data.datasource.remote.PostCreatingAliasesDataSource
import com.tekehome.takehome.data.datasource.remote.model.request.UrlData
import com.tekehome.takehome.data.datasource.remote.model.response.ServiceAliasesData
import com.tekehome.takehome.util.MainDispatcherRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class TakeHomeDataRepositoryTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Inject
    lateinit var takeHomeDataRepository: TakeHomeDataRepository
    private val postCreatingAliasesDataSource = mock<PostCreatingAliasesDataSource>()
    private val getAliasesDataSource = mock<GetAliasesDataSource>()

    companion object {
        private const val NU_URL =
            "https://nu.com.mx/?cq_plac=&cq_net=g&cq_pos=&cq_med=&cq_plt=gp&utm_source=google&utm_medium=cpc&utm_campaign=15501076442&utm_term=129518566045&utm_word=nubank&utm_content=650819243402&ad_position=&match_type=e&location=9133506&device=c&utm_keyword_id=kwd-306018933097&gad_source=1&gclid=Cj0KCQiA7aSsBhCiARIsALFvovyGKIG5Rbq6zFEJSR6HXtVo-BxXdUGK1ksc9QzIGj-i0aqpQjQQ6A4aApgXEALw_wcB"
        private const val ALIAS = "50741931"
    }

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun `Given post Creating Aliases Repository then get Successful`() = runTest {
        val aliasesData = ServiceAliasesData(
            ALIAS,
            self = "nu",
            short = NU_URL,
        )

        postCreatingAliasesDataSource.stub {
            on {
                postCreatingAliasesSource(UrlData(NU_URL))
            }.doReturn(flowOf(aliasesData))
        }

        val serviceAliasesData = takeHomeDataRepository.postCreatingAliases(NU_URL)
        advanceUntilIdle()
        serviceAliasesData.collect {
            MatcherAssert.assertThat(
                it,
                CoreMatchers.instanceOf(ServiceAliasesData::class.java),
            )
            Assert.assertTrue(it.alias.isNotEmpty())
            Assert.assertTrue(it.short.isNotEmpty())
            Assert.assertTrue(it.self.isNotEmpty())
        }
    }

    @Test
    fun `Given get Aliases Repository then get Successful`() = runTest {
        getAliasesDataSource.stub {
            on {
                getAliasesSource(ALIAS)
            }.doReturn(flowOf(NU_URL))
        }
        val getUrlAliases = takeHomeDataRepository.getUrlAliases(ALIAS)
        advanceUntilIdle()
        getUrlAliases.collect {
            Assert.assertEquals(it, NU_URL)
            Assert.assertTrue(it.isNotEmpty())
        }
    }
}
