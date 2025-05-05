package com.aamagon.practica_fct_android.ui.view.screens

import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import javax.inject.Inject

class States @Inject constructor() {

    // States for DatePicker
    var showFrom = mutableStateOf(false)
    var showTo = mutableStateOf(false)
    var selectedDateFrom = mutableStateOf<Long?>(null)
    var dateStringFrom = mutableStateOf("Día/Mes/Año")
    var selectedDateTo = mutableStateOf<Long?>(null)
    var dateStringTo = mutableStateOf("Día/Mes/Año")

    // State to control the Slider
    var sliderPos = mutableFloatStateOf(0F)

    // States for each CheckBox
    var paidChecked = mutableStateOf(false)
    var cancelledChecked = mutableStateOf(false)
    var fixedFeeChecked = mutableStateOf(false)
    var waitingChecked = mutableStateOf(false)
    var paymentPlanChecked = mutableStateOf(false)

    fun resetFilterValues(){
        showFrom.value = false
        showTo.value = false
        selectedDateFrom.value = null
        dateStringFrom.value = "Día/Mes/Año"
        selectedDateTo.value = null
        dateStringTo.value = "Día/Mes/Año"

        sliderPos.floatValue = 0f

        paidChecked.value = false
        cancelledChecked.value = false
        fixedFeeChecked.value = false
        waitingChecked.value = false
        paymentPlanChecked.value = false
    }

    fun showValues(string: String){
        Log.e("from", string)
        Log.e("1", selectedDateFrom.value.toString())
        Log.e("2", dateStringFrom.value)
        Log.e("3", selectedDateTo.value.toString())
        Log.e("4", dateStringTo.value)

        Log.e("5", sliderPos.floatValue.toString())

        Log.e("6", paidChecked.value.toString())
        Log.e("7", cancelledChecked.value.toString())
        Log.e("8", fixedFeeChecked.value.toString())
        Log.e("9", waitingChecked.value.toString())
        Log.e("10", paymentPlanChecked.value.toString())
    }
}