package com.aamagon.practica_fct_android.domain

import com.aamagon.practica_fct_android.data.Repository
import com.aamagon.practica_fct_android.domain.model.Bill
import javax.inject.Inject

class FilterBillsUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): List<Bill> = repository.getAllBillsFromDatabase().bills
}