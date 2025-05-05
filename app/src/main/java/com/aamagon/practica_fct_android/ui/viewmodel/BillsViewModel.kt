package com.aamagon.practica_fct_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamagon.practica_fct_android.domain.FilterBillsUseCase
import com.aamagon.practica_fct_android.domain.GetBillsUseCase
import com.aamagon.practica_fct_android.domain.model.Bill
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillsViewModel @Inject constructor(
    private val getBillsUseCase: GetBillsUseCase,
    private val filterBillsUseCase: FilterBillsUseCase
): ViewModel() {

    private val _billsList = MutableLiveData<List<Bill>>()
    val billsList: LiveData<List<Bill>> = _billsList

    private val _appliedFilters = MutableLiveData<Boolean>()
    val appliedFilters: LiveData<Boolean> = _appliedFilters

    init {
        viewModelScope.launch {
            val result = getBillsUseCase()

            if (result.isNotEmpty()){
                _billsList.postValue(result)
            }
        }
    }

    fun applyFilters(){
        viewModelScope.launch {
            val result = filterBillsUseCase()

            applied(true)

            _billsList.postValue(result)
        }
    }

    fun applied(value: Boolean){
        _appliedFilters.postValue(value)
    }

}