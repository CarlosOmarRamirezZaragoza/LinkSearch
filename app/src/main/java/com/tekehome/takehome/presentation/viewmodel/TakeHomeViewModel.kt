package com.tekehome.takehome.presentation.viewmodel

import android.webkit.URLUtil
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.test.espresso.idling.CountingIdlingResource
import com.tekehome.takehome.domain.entity.ServiceAliases
import com.tekehome.takehome.domain.entity.TakeHomeDataBase
import com.tekehome.takehome.domain.usecase.AddTakeHomeUseCase
import com.tekehome.takehome.domain.usecase.CreatingAliasesUseCase
import com.tekehome.takehome.domain.usecase.DeleteTakeHomeUseCase
import com.tekehome.takehome.domain.usecase.GetAllTakeHomeUseCase
import com.tekehome.takehome.domain.usecase.GetUrlAliasesUseCase
import com.tekehome.takehome.domain.usecase.UpdateTakeHomeUseCase
import com.tekehome.takehome.presentation.model.Status
import com.tekehome.takehome.presentation.resources.TakeHomeResourcesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TakeHomeViewModel @Inject constructor(
    private val creatingAliasesUseCase: CreatingAliasesUseCase,
    private val addTaskUseCase: AddTakeHomeUseCase,
    private val updateTaskUseCase: UpdateTakeHomeUseCase,
    private val deleteTaskUseCase: DeleteTakeHomeUseCase,
    private val getAllTakeHomeUseCase: GetAllTakeHomeUseCase,
    private val getUrlAliasesUseCase: GetUrlAliasesUseCase,
    private val takeHomeResourcesManager: TakeHomeResourcesManager,
) : ViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val _statusFlow = MutableSharedFlow<Status>()
    var statusFlow = _statusFlow.asSharedFlow()

    companion object {
        private const val NUMBER_REGEX = "^[0-9]+$"
        private const val DATA_LOADER = "dataLoader"
    }

    val idlingResource = CountingIdlingResource(DATA_LOADER)

    init {
        onGetAllTakeHomeItems()
    }

    private fun onSetStatusFlow(status: Status) {
        viewModelScope.launch {
            _statusFlow.emit(status)
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onCreateAliases(url: String) {
        val regex = NUMBER_REGEX.toRegex()
        viewModelScope.launch {
            if (regex.matches(url)) {
                getUrlAliasesUseCase(url).catch {
                    onSetStatusFlow(Status.Failure(it.message.toString()))
                }.collect {
                    onAddAndGetItems(
                        ServiceAliases(alias = url, self = it),
                    )
                }
            } else if (URLUtil.isValidUrl(url)) {
                creatingAliasesUseCase(url).catch {
                    onSetStatusFlow(Status.Failure(it.message.toString()))
                }.collect { serviceAliases ->
                    onAddAndGetItems(serviceAliases)
                }
            } else {
                onSetStatusFlow(Status.Failure(takeHomeResourcesManager.getUrlErrorMessage()))
            }
        }
    }

    private fun onAddAndGetItems(serviceAliases: ServiceAliases) {
        onAddTakeHomeItem(serviceAliases)
        onGetAllTakeHomeItems()
    }

    private fun onGetAllTakeHomeItems() {
        viewModelScope.launch {
            getAllTakeHomeUseCase().catch {
                onSetStatusFlow(Status.Failure(takeHomeResourcesManager.getNoUrlSavedErrorMessage()))
            }.collect { takeHomeDataBaseList ->
                onSetStatusFlow(
                    Status.Success(
                        takeHomeDataBaseList.map { takeHomeDataBase ->
                            ServiceAliases(
                                takeHomeDataBase.alias,
                                takeHomeDataBase.self,
                                takeHomeDataBase.shortUrl,
                            )
                        },
                    ),
                )
            }
        }
    }

    private fun onAddTakeHomeItem(serviceAliases: ServiceAliases) {
        viewModelScope.launch {
            addTaskUseCase(
                TakeHomeDataBase(
                    alias = serviceAliases.alias,
                    self = serviceAliases.self,
                    shortUrl = serviceAliases.shortUrl,
                ),
            )
        }
    }

    fun onIncrementIdLingResource() = idlingResource.increment()
    fun onDecrementIdLingResource() = idlingResource.decrement()
    fun onLingResourceActive() = idlingResource.isIdleNow
}
