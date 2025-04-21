package com.aamagon.practica_fct_android.ui.view.screens

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

class States {

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

    // States for DetailTab in SmartSolar
    var cau = mutableStateOf("")
    var membershipState = mutableStateOf("")
    var type = mutableStateOf("")
    var excessCompensation = mutableStateOf("")
    var power = mutableStateOf("")

    // State DetailTab Dialog
    var showDetailDialog = mutableStateOf(false)

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
}