package com.aks.parkingapp.data.local.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(
    private val prefs: SharedPreferences
) {

    // -----------------------------
    // Generic Methods
    // -----------------------------

    private fun putString(
        key: String,
        value: String
    ) {
        prefs.edit { putString(key, value) }
    }

    private fun getString(
        key: String
    ): String? {

        return prefs.getString(key, "")
    }

    private fun putBoolean(
        key: String,
        value: Boolean
    ) {
        prefs.edit { putBoolean(key, value) }
    }

    private fun getBoolean(
        key: String
    ): Boolean {

        return prefs.getBoolean(key, false)
    }

    private  fun clear() {
        prefs.edit { clear() }
    }


    // -----------------------------
    // Public Methods
    // -----------------------------

    fun setOnBoardingCompleted(value: Boolean) {
        putBoolean(SecureKeys.KEY_APP_LAUNCH_FIRST_TIME, value)
    }

    fun getOnboardingCompleted(): Boolean {
        return getBoolean(SecureKeys.KEY_APP_LAUNCH_FIRST_TIME)
    }

    fun setRegistrationCompleted(value: Boolean) {
        putBoolean(SecureKeys.KEY_USER_REGISTER, value)
    }

    fun getRegistrationCompleted(): Boolean {
        return getBoolean(SecureKeys.KEY_USER_REGISTER)
    }


    fun setToken(value: String) {
        putString(SecureKeys.KEY_TOKEN, value)
    }

    fun getToken(): String? {
        return getString(SecureKeys.KEY_TOKEN)
    }

}