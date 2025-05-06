package com.aamagon.practica_fct_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamagon.practica_fct_android.domain.FilterBillsUseCase
import com.aamagon.practica_fct_android.domain.GetBillsUseCase
import com.aamagon.practica_fct_android.domain.model.Bill
import com.aamagon.practica_fct_android.ui.view.screens.States
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

    // An instance to save the first list received
    private var original = emptyList<Bill>()

    init {
        viewModelScope.launch {
            val result = getBillsUseCase()

            original = result

            if (result.isNotEmpty()){
                _billsList.postValue(result)
            }
        }
    }

    fun applyFilters(states: States){
        viewModelScope.launch {
            val result = filterBillsUseCase(states)

            _billsList.postValue(result)
        }
    }

    fun reset(){
        _billsList.postValue(original)
    }

}