package com.aamagon.practica_fct_android.domain.model

import com.aamagon.practica_fct_android.data.BillsRepository
import com.aamagon.practica_fct_android.data.model.BillModel
import javax.inject.Inject

class GetBillsUseCase @Inject constructor( private val repository: BillsRepository ) {
    suspend operator fun invoke(): List<BillModel> = repository.getAllBills().bills
}