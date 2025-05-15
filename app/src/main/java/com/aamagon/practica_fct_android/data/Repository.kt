package com.aamagon.practica_fct_android.data

import com.aamagon.practica_fct_android.core.extensions.toDomain
import com.aamagon.practica_fct_android.data.database.dao.BillDao
import com.aamagon.practica_fct_android.data.database.entities.BillEntity
import com.aamagon.practica_fct_android.data.model.BillsListModel
import com.aamagon.practica_fct_android.data.model.MockButtonPreference
import com.aamagon.practica_fct_android.data.network.bills.BillsApiService
import com.aamagon.practica_fct_android.data.network.detail.DetailApiService
import com.aamagon.practica_fct_android.domain.model.BillsList
import com.aamagon.practica_fct_android.domain.model.Detail
import javax.inject.Inject

class Repository @Inject constructor(
    private val billApi: BillsApiService,
    private val detailApi: DetailApiService,
    private val billDao: BillDao,
    private val activateMocks: MockButtonPreference
) {

    // Functions related to services
    suspend fun getAllBillsFromApi(): BillsList{
        var response: BillsListModel = billApi.getBills()
        return response.toDomain()
    }

    suspend fun getDetailFromApi(): Detail{
        val response = detailApi.getDetail()
        return response.toDomain()
    }

    // Functions about database management
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

    // Functions related to the shared preferences management
    fun setUseMock(value: Boolean) = activateMocks.setValue(value)
    fun getUseMock(): Boolean = activateMocks.getValue()
}