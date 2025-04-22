package com.aamagon.practica_fct_android.domain.model

import com.aamagon.practica_fct_android.data.BillsRepository
import com.aamagon.practica_fct_android.data.model.BillModel

class GetBillsUseCase {

    private val repository = BillsRepository()

    suspend operator fun invoke(): List<BillModel> = repository.billsList.bills
}