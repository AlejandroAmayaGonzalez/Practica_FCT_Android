package com.aamagon.practica_fct_android.data.model

import android.content.SharedPreferences
import javax.inject.Inject
import androidx.core.content.edit

class MockButtonPreference @Inject constructor(
   private val prefs: SharedPreferences
) {
    fun setValue(value: Boolean) = prefs.edit() { putBoolean("use_mock", value) }
    fun getValue(): Boolean = prefs.getBoolean("use_mock", false)
}