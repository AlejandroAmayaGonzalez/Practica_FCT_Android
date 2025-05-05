package com.aamagon.practica_fct_android.domain

import android.content.Context
import android.util.Log
import com.aamagon.practica_fct_android.R
import com.aamagon.practica_fct_android.data.Repository
import com.aamagon.practica_fct_android.domain.model.Bill
import com.aamagon.practica_fct_android.ui.view.screens.States
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FilterBillsUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: Repository
) {

    suspend operator fun invoke(states: States): List<Bill> {

        var list = repository.getAllBillsFromDatabase().bills

        list.filter { it.status == context.getString(R.string.paid) }
        /*val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")

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
        }*/

        Log.e("BD", "$list")
        states.showValues("UseCase")
        return list
    }
}