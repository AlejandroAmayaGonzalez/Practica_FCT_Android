package com.aamagon.practica_fct_android.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamagon.practica_fct_android.R
import com.aamagon.practica_fct_android.domain.GetBillsUseCase
import com.aamagon.practica_fct_android.domain.model.Bill
import com.aamagon.practica_fct_android.ui.view.screens.States
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getBillsUseCase: GetBillsUseCase,
): ViewModel() {

    private val _filteredBills = MutableLiveData<List<Bill>>()
    val filteredBills: LiveData<List<Bill>> = _filteredBills

    init {
        viewModelScope.launch {
            val result = getBillsUseCase()

            if (result.isNotEmpty()){
                _filteredBills.postValue(result)
            }
        }
    }

    fun applyFilters(states: States){

        var list = filteredBills.value ?: emptyList()
        val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        // Date filter
        if (states.dateStringFrom.value == "Día/Mes/Año" && states.dateStringTo.value == "Día/Mes/Año"){
            list.filter {
                var date = LocalDate.parse(it.date, format)
                date.isAfter(LocalDate.MIN) && date.isBefore(LocalDate.MAX)
            }
        }else if (states.dateStringFrom.value != "Día/Mes/Año" && states.dateStringTo.value == "Día/Mes/Año"){
            list.filter {
                var date = LocalDate.parse(it.date, format)
                date.isAfter(LocalDate.parse(states.dateStringFrom.value, format)) && date.isBefore(LocalDate.MAX)
            }
        }else if (states.dateStringFrom.value == "Día/Mes/Año" && states.dateStringTo.value != "Día/Mes/Año"){
            list.filter {
                var date = LocalDate.parse(it.date, format)
                date.isAfter(LocalDate.MIN) && date.isBefore(LocalDate.parse(states.dateStringTo.value, format))
            }
        }else{
            list.filter {
                var date = LocalDate.parse(it.date, format)
                date.isAfter(LocalDate.parse(states.dateStringFrom.value, format)) &&
                        date.isBefore(LocalDate.parse(states.dateStringTo.value, format))
            }
        }

        // Quantity filter
        if (states.sliderPos.floatValue != 0F){
            list.filter {
                var valueSlider = states.sliderPos.floatValue.toDouble()
                it.quantity >= 1 && it.quantity <= valueSlider
            }
        }

        // CheckBox filters
        if (states.paidChecked.value){
            list.filter { it.status == context.getString(R.string.paid) }
        }
        if (states.cancelledChecked.value){
            list.filter { it.status == context.getString(R.string.cancelled) }
        }
        if (states.fixedFeeChecked.value){
            list.filter { it.status == context.getString(R.string.fixedFee) }
        }
        if (states.waitingChecked.value){
            list.filter { it.status == context.getString(R.string.waitingForPayment) }
        }
        if (states.paymentPlanChecked.value){
            list.filter { it.status == context.getString(R.string.paymentPlan) }
        }

        // Update the ViewModel with the filters applied list
        _filteredBills.postValue(list)
    }
}