package com.aamagon.practica_fct_android.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamagon.practica_fct_android.data.model.BillModel
import com.aamagon.practica_fct_android.domain.model.GetBillsUseCase
import kotlinx.coroutines.launch

class BillsViewModel: ViewModel() {

    val billsList = MutableLiveData<List<BillModel>>()

    var getBillsUseCase = GetBillsUseCase()

    fun onCreate(){
        viewModelScope.launch {
            val result = getBillsUseCase()

            if (result.isNotEmpty()){
                billsList.postValue(result)
            }
        }
    }

}