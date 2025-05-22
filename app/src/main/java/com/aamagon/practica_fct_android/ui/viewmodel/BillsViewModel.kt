package com.aamagon.practica_fct_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamagon.practica_fct_android.domain.FilterBillsUseCase
import com.aamagon.practica_fct_android.domain.GetBillsUseCase
import com.aamagon.practica_fct_android.domain.PrefsManagementUseCase
import com.aamagon.practica_fct_android.domain.model.Bill
import com.aamagon.practica_fct_android.ui.view.screens.States
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class BillsViewModel @Inject constructor(
    private val getBillsUseCase: GetBillsUseCase,
    private val filterBillsUseCase: FilterBillsUseCase,
    private val prefsManagementUseCase: PrefsManagementUseCase
): ViewModel() {

    private val _billsList = MutableLiveData<List<Bill>>()
    val billsList: LiveData<List<Bill>> = _billsList

    // To manage the noMatches toast
    private val _noMatches = MutableLiveData<Boolean>(false)
    val noMatches: LiveData<Boolean> = _noMatches

    // Show a progress indicator
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    // An instance to save the first list received
    private var original = emptyList<Bill>()

    init {
        viewModelScope.launch {
            _isLoading.postValue(true)

            val result = getBillsUseCase()

            original = result

            if (result.isNotEmpty()){
                _billsList.postValue(result)
            }

            _isLoading.postValue(false)
        }
    }

    fun applyFilters(states: States){
        viewModelScope.launch {
            val result = filterBillsUseCase(states)

            _billsList.postValue(result)

            if (billsList.value?.isEmpty() == true){
                _noMatches.postValue(true)
            }
        }
    }

    // Function that returns a date with this style: "28 Ago 2020"
    fun billCardDateFormat(date: String): String{
        val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val parsedDate = LocalDate.parse(date, format)

        // Format the months like Ene, Feb, Mar...
        val month = when (parsedDate.month.toString()){
            "JANUARY" -> "Ene"
            "FEBRUARY" -> "Feb"
            "MARCH" -> "Mar"
            "APRIL" -> "Abr"
            "MAY" -> "May"
            "JUNE" -> "Jun"
            "JULY" -> "Jul"
            "AUGUST" -> "Ago"
            "SEPTEMBER" -> "Sep"
            "OCTOBER" -> "Oct"
            "NOVEMBER" -> "Nov"
            "DECEMBER" -> "Dec"
            else -> ""
        }

        return "${parsedDate.dayOfMonth} $month ${parsedDate.year}"
    }

    fun resetNoMatchValue() = _noMatches.postValue(false)
    fun changeValuePref(value: Boolean) = prefsManagementUseCase.setValuePref(value)
    fun getValuePref(): Boolean = prefsManagementUseCase.getValuePref()

    fun reset() = _billsList.postValue(original)
}