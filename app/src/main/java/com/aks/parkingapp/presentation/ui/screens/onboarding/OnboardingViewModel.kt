package com.aks.parkingapp.presentation.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import com.aks.parkingapp.data.local.preferences.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val preferenceManager: PreferencesManager
): ViewModel() {

    fun completeOnboarding() {
        preferenceManager.setOnBoardingCompleted(true)
    }

}