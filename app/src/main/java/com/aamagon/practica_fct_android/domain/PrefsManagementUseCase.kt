package com.aamagon.practica_fct_android.domain

import com.aamagon.practica_fct_android.data.Repository
import javax.inject.Inject

class PrefsManagementUseCase @Inject constructor(
    private val repository: Repository
) {
    fun getValuePref(): Boolean = repository.getUseMock()
    fun setValuePref(value: Boolean) = repository.setUseMock(value)
}