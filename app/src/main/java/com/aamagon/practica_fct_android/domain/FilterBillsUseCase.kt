package com.aamagon.practica_fct_android.domain

import android.content.Context
import com.aamagon.practica_fct_android.R
import com.aamagon.practica_fct_android.data.Repository
import java.time.LocalDate
import com.aamagon.practica_fct_android.domain.model.Bill
import com.aamagon.practica_fct_android.ui.view.screens.States
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FilterBillsUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: Repository
) {

    suspend operator fun invoke(states: States): List<Bill> {

        var list = repository.getAllBillsFromDatabase().bills
        val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        var result = list.asSequence()
            .filter {
                var date = LocalDate.parse(it.date, format)
                dateFilter(states, date)
            }

        /*if (states.dateStringFrom.value == "Día/Mes/Año" && states.dateStringTo.value == "Día/Mes/Año"){
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
        }*/

        // Quantity filter
        /*if (states.sliderPos.floatValue != 0F){
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

        return result.toList()
    }

    private fun dateFilter(states: States, date: LocalDate): Boolean{
        val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val placeholder = context.getString(R.string.datePlaceholder)

        // There are 4 possibilities. Return the necessary code to filter
        return if (states.dateStringFrom.value == placeholder && states.dateStringTo.value == placeholder){
            date.isAfter(LocalDate.MIN) && date.isBefore(LocalDate.MAX)
        }else if (states.dateStringFrom.value != placeholder && states.dateStringTo.value == placeholder){
            date.isAfter(LocalDate.parse(states.dateStringFrom.value, format))
                    && date.isBefore(LocalDate.MAX)
        }else if (states.dateStringFrom.value == placeholder && states.dateStringTo.value != placeholder){
            date.isAfter(LocalDate.MIN)
                    && date.isBefore(LocalDate.parse(states.dateStringTo.value, format))
        }else{
            date.isAfter(LocalDate.parse(states.dateStringFrom.value, format)) &&
                    date.isBefore(LocalDate.parse(states.dateStringTo.value, format))
        }
    }
}