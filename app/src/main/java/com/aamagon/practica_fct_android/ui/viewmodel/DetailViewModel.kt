package com.aamagon.practica_fct_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamagon.practica_fct_android.domain.GetDetailUseCase
import com.aamagon.practica_fct_android.domain.model.Detail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailUseCase: GetDetailUseCase
): ViewModel() {

    private val _detailItem = MutableLiveData<Detail>()
    val detail: LiveData<Detail> = _detailItem

    init {
        viewModelScope.launch {
            val result = getDetailUseCase()

            checkValues(result)

            _detailItem.postValue(result)
        }
    }

    fun getDetail(): Detail?{
        return detail.value
    }

    fun checkValues(result: Detail) {
        println(result.cau)
        println(result.state)
        println(result.type)
        println(result.compensation)
        println(result.power)

        println(_detailItem.value?.cau)
        println(_detailItem.value?.state)
        println(_detailItem.value?.type)
        println(_detailItem.value?.compensation)
        println(_detailItem.value?.power)
    }
}