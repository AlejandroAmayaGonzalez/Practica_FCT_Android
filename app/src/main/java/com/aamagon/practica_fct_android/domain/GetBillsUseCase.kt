package com.aamagon.practica_fct_android.domain

import com.aamagon.practica_fct_android.core.extensions.toDatabase
import com.aamagon.practica_fct_android.data.Repository
import com.aamagon.practica_fct_android.domain.model.Bill
import javax.inject.Inject

class GetBillsUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): List<Bill>{
        val billsList = repository.getAllBillsFromApi()

        return if (billsList.bills.isNotEmpty()){
            repository.clearBills()

            repository.insertBills(billsList.bills.map {
                it.toDatabase(id = billsList.bills.indexOf(it))
            })

            billsList.bills
        }else{
            repository.getAllBillsFromDatabase().bills
        }
    }
}