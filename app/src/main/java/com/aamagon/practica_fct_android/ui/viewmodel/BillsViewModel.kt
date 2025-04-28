package com.aamagon.practica_fct_android.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamagon.practica_fct_android.domain.GetBillsUseCase
import com.aamagon.practica_fct_android.domain.model.Bill
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillsViewModel @Inject constructor(
    private val getBillsUseCase: GetBillsUseCase
): ViewModel() {

    val billsList = MutableLiveData<List<Bill>>()

    init {
        viewModelScope.launch {
            val result = getBillsUseCase()

            if (result.isNotEmpty()){
                billsList.postValue(result)
            }
        }
    }
}