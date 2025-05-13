package com.aamagon.practica_fct_android.domain

import com.aamagon.practica_fct_android.data.Repository
import com.aamagon.practica_fct_android.domain.model.Bill
import com.aamagon.practica_fct_android.domain.model.BillsList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetBillsUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: Repository

    lateinit var getBillsUseCase: GetBillsUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getBillsUseCase = GetBillsUseCase(repository)
    }

    @Test
    fun `when the api doesn't return anything then get values from database`() = runBlocking {
        // Given
        coEvery { repository.getAllBillsFromApi() } returns BillsList(0, emptyList())

        // When
        getBillsUseCase()

        // Then
        coVerify(exactly = 1) { repository.getAllBillsFromDatabase() }
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        // Given
        val myList = listOf(Bill("Pagada", 21.89, "10/07/2003"))
        coEvery { repository.getAllBillsFromApi() } returns BillsList(1, myList)

        // When
        val res = getBillsUseCase()

        // Then
        coVerify(exactly = 1) { repository.clearBills() }
        coVerify(exactly = 1) { repository.insertBills(any()) }
        coVerify(exactly = 0) { repository.getAllBillsFromDatabase() }
        assert(res == myList)
    }
}