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
            .filter {
                sliderFilter(states, it.quantity)
            }
            .filter {
                checkBoxFilter(states.paidChecked.value,
                    context.getString(R.string.filPaid), it.status)
            }
            .filter {
                checkBoxFilter(states.cancelledChecked.value,
                    context.getString(R.string.filCancel), it.status)
            }
            .filter {
                checkBoxFilter(states.fixedFeeChecked.value,
                    context.getString(R.string.filFixed), it.status)
            }
            .filter {
                checkBoxFilter(states.waitingChecked.value,
                    context.getString(R.string.filWaiting), it.status)
            }
            .filter {
                checkBoxFilter(states.paymentPlanChecked.value,
                    context.getString(R.string.filPlan), it.status)
            }

        return result.toList()
    }

    private fun dateFilter(states: States, date: LocalDate): Boolean{
        val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val placeholder = context.getString(R.string.datePlaceholder)

        val dateFrom = states.dateStringFrom.value
        val dateTo = states.dateStringTo.value

        // There are 4 possibilities. Return the necessary code to filter
        return if (dateFrom == placeholder && dateTo == placeholder){

            // The date filter is not used
            date.isAfter(LocalDate.MIN) && date.isBefore(LocalDate.MAX)

        }else if (dateFrom != placeholder && dateTo == placeholder){

            // Only "from" date is changed
            date.isAfter(LocalDate.parse(dateFrom, format))
                    && date.isBefore(LocalDate.MAX)

        }else if (dateFrom == placeholder){

            // Only "to" date is changed
            date.isAfter(LocalDate.MIN)
                    && date.isBefore(LocalDate.parse(dateTo, format))

        }else{

            // Both are changed
            date.isAfter(LocalDate.parse(dateFrom, format)) &&
                    date.isBefore(LocalDate.parse(dateTo, format))
        }
    }

    private fun sliderFilter(states: States, quantity: Double): Boolean{
        val sliderVal = states.sliderPos.floatValue.toDouble()

        return if (states.sliderPos.floatValue != states.minSlider.toFloat()){
            quantity >= 1.0 && quantity <= sliderVal
        }else{
            quantity >= 1.0 && quantity <= states.maxSlider
        }
    }

    private fun checkBoxFilter(
        checkboxState: Boolean,
        stringCompared: String,
        status: String
    ): Boolean{
        return if (checkboxState){
            status.equals(stringCompared, true)
        } else true
    }
}