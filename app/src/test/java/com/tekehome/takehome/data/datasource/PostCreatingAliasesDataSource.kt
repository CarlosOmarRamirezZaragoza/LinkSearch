package com.tekehome.takehome.data.datasource

import com.tekehome.takehome.data.datasource.remote.PostCreatingAliasesDataSource
import com.tekehome.takehome.data.datasource.remote.model.request.UrlData
import com.tekehome.takehome.data.datasource.remote.model.response.ServiceAliasesData
import com.tekehome.takehome.util.MainDispatcherRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.HttpException
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class PostCreatingAliasesDataSource {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Inject
    lateinit var postCreatingAliasesDataSource: PostCreatingAliasesDataSource

    companion object {
        private const val SHORTER_URL = "https://url-shortener-server.onrender.com"
        private const val EMPTY_URL = ""
        private val urlData = UrlData(SHORTER_URL)
        private val urlEmpty = UrlData(EMPTY_URL)
    }

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun `Given post Creating Aliases Source then get Successful`() = runTest {
        val serviceAliasesData = postCreatingAliasesDataSource.postCreatingAliasesSource(urlData)
        advanceUntilIdle()
        serviceAliasesData.collect {
            assertThat(it, instanceOf(ServiceAliasesData::class.java))
            assertTrue(it.alias.isNotEmpty())
            assertTrue(it.short.isNotEmpty())
            assertTrue(it.self.isNotEmpty())
        }
    }

    @Test(expected = HttpException::class)
    fun `Given no post Creating Aliases Source then get Failure`() = runTest {
        val serviceAliasesData = postCreatingAliasesDataSource.postCreatingAliasesSource(urlEmpty)
        advanceUntilIdle()
        serviceAliasesData.collect {
            println(it)
        }
    }
}
