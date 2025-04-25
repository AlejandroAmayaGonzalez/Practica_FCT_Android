package com.aamagon.practica_fct_android.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamagon.practica_fct_android.data.model.BillModel
import com.aamagon.practica_fct_android.domain.model.GetBillsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillsViewModel @Inject constructor(
    private val getBillsUseCase: GetBillsUseCase
): ViewModel() {

    val billsList = MutableLiveData<List<BillModel>>()

    init {
        viewModelScope.launch {
            val result = getBillsUseCase()

            if (result.isNotEmpty()){
                billsList.postValue(result)
            }
        }
    }
}