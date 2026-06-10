package com.aks.parkingapp.presentation.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.parkingapp.data.local.preferences.PreferencesManager
import com.aks.parkingapp.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val preferenceManager: PreferencesManager
): ViewModel()
{
    private val _startDestination =
        MutableStateFlow<String?>(null)

    val startDestination =
        _startDestination.asStateFlow()

    init {
        checkNavigation()
    }

    private fun checkNavigation() {

        viewModelScope.launch {

            delay(1500.milliseconds)

            val onBoardingCompleted = preferenceManager.getOnboardingCompleted()
            val onRegistrationCompleted =preferenceManager.getRegistrationCompleted()

            _startDestination.value =
                if (onBoardingCompleted && onRegistrationCompleted) {
                    Routes.VERIFY_OTP
                }
                else if (onBoardingCompleted){
                    Routes.REGISTER
                }
                else {
                    Routes.ONBOARDING
                }
        }
    }

}