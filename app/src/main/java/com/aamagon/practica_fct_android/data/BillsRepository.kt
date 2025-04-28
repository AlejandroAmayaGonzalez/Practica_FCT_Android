package com.aamagon.practica_fct_android.data

import com.aamagon.practica_fct_android.core.extensions.toDomain
import com.aamagon.practica_fct_android.data.database.dao.BillDao
import com.aamagon.practica_fct_android.data.database.entities.BillEntity
import com.aamagon.practica_fct_android.data.network.ApiService
import com.aamagon.practica_fct_android.domain.model.BillsList
import javax.inject.Inject

class BillsRepository @Inject constructor(
    private val api: ApiService,
    private val billDao: BillDao
) {

    suspend fun getAllBillsFromApi(): BillsList{
        val response = api.getBills()
        return response.toDomain()
    }

    suspend fun getAllBillsFromDatabase(): BillsList{
        val response = billDao.getAllBills()

        // Map the entities to a list of bills
        val aux = response.map { it.toDomain() }

        // Return the object with the size and list
        return BillsList(aux.size, aux)
    }

    suspend fun insertBills(bills: List<BillEntity>){
        billDao.insertAll(bills)
    }

    suspend fun clearBills() = billDao.deleteBills()
}