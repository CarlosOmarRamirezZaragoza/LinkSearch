package com.tekehome.takehome.presentation.viewmodel

import com.tekehome.takehome.domain.entity.ServiceAliases
import com.tekehome.takehome.domain.usecase.CreatingAliasesUseCase
import com.tekehome.takehome.domain.usecase.GetUrlAliasesUseCase
import com.tekehome.takehome.presentation.model.Status
import com.tekehome.takehome.presentation.resources.TakeHomeResourcesManager
import com.tekehome.takehome.util.MainDispatcherRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import org.mockito.kotlin.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class TakeHomeViewModelTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Inject
    lateinit var takeHomeResourcesManager: TakeHomeResourcesManager

    private val statusTest =
        mock<MutableSharedFlow<Status>> {}

    private val takeHomeViewModel = mock<TakeHomeViewModel>()

    private val creatingAliasesUseCase = mock<CreatingAliasesUseCase>()

    private val getUrlAliasesUseCase = mock<GetUrlAliasesUseCase>()

    companion object {
        private const val NU_URL =
            "https://nu.com.mx/?cq_plac=&cq_net=g&cq_pos=&cq_med=&cq_plt=gp&utm_source=google&utm_medium=cpc&utm_campaign=15501076442&utm_term=129518566045&utm_word=nubank&utm_content=650819243402&ad_position=&match_type=e&location=9133506&device=c&utm_keyword_id=kwd-306018933097&gad_source=1&gclid=Cj0KCQiA7aSsBhCiARIsALFvovyGKIG5Rbq6zFEJSR6HXtVo-BxXdUGK1ksc9QzIGj-i0aqpQjQQ6A4aApgXEALw_wcB"
        private const val ALIAS = "50741931"
        private const val BAD_NU_URL = "https//nu.co"
    }

    @Before
    fun init() {
        hiltRule.inject()
        takeHomeViewModel.statusFlow = statusTest
    }

    @Test
    fun `Given on Create Aliases correct URL Successful`() = runTest {
        creatingAliasesUseCase.stub {
            on {
                invoke(NU_URL)
            }.doReturn(
                flowOf(
                    ServiceAliases(
                        ALIAS,
                        NU_URL,
                        NU_URL,
                    ),
                ),
            )
        }
        val aliasesList = mutableListOf<ServiceAliases>()
        aliasesList.add(ServiceAliases(ALIAS, NU_URL, NU_URL))
        takeHomeViewModel.onCreateAliases(NU_URL)
        advanceUntilIdle()
        launch {
            verify(statusTest).emit(Status.Success(aliasesList))
        }
    }

    @Test
    fun `Given on Get Aliases correct URL Successful`() = runTest {
        getUrlAliasesUseCase.stub {
            on {
                invoke(ALIAS)
            }.doReturn(
                flowOf(NU_URL),
            )
        }
        val aliasesList = mutableListOf<ServiceAliases>()
        aliasesList.add(ServiceAliases(ALIAS, NU_URL))
        takeHomeViewModel.onCreateAliases(ALIAS)
        advanceUntilIdle()
        launch {
            verify(statusTest).emit(Status.Success(aliasesList))
        }
    }

    @Test
    fun `Given on Get Aliases URL Failed`() = runTest {
        getUrlAliasesUseCase.stub {
            on {
                invoke(BAD_NU_URL)
            }.doReturn(
                flowOf(NU_URL),
            )
        }
        takeHomeViewModel.onCreateAliases(BAD_NU_URL)
        advanceUntilIdle()
        launch {
            verify(statusTest).emit(Status.Failure(takeHomeResourcesManager.getUrlErrorMessage()))
        }
    }
}
