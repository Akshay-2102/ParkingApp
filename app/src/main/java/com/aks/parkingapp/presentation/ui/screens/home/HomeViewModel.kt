package com.aks.parkingapp.presentation.ui.screens.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.aks.parkingapp.domain.usecases.GetUserDetailsUseCase
import com.aks.parkingapp.services.SyncWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    application: Application,
     private val getUserDetailsUseCase: GetUserDetailsUseCase
): AndroidViewModel(application) {

    init {
        startService()
    }

    // Work manger call
    fun startService(){
        // One time request
        val request = OneTimeWorkRequestBuilder<SyncWorker>().build()
        WorkManager.getInstance(getApplication()).enqueue(request)
    }


    // --------------------------------
    // STATE FLOW
    // --------------------------------
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    // --------------------------------
    // SHARED FLOW
    // --------------------------------
    private val _event = MutableSharedFlow<HomeUiEvent>()
    val event = _event.asSharedFlow()


    init {
        getUserDetails()
    }

    fun getUserDetails() {

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }

            try {

                val result =
                    getUserDetailsUseCase()

                result.onSuccess { response ->

                    _uiState.update {
                        it.copy(
                            userName = response.name,
                            userEmail = response.email
                        )
                    }

                    _event.emit(
                        HomeUiEvent.ShowSuccess(
                            response.name
                        )
                    )

                }.onFailure {

                    _uiState.update {
                        it.copy(
                            userName = "NA",
                            userEmail = "NA"
                        )
                    }

                    _event.emit(
                        HomeUiEvent.ShowError(
                            it.message ?: "Error"
                        )
                    )
                }

            } catch (e: Exception) {

                _event.emit(
                    HomeUiEvent.ShowError(
                        e.message ?: "Unknown Error"
                    )
                )

            } finally {

                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }


}