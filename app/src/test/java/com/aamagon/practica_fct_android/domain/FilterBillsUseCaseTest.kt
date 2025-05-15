package com.aamagon.practica_fct_android.domain

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aamagon.practica_fct_android.R
import com.aamagon.practica_fct_android.data.Repository
import com.aamagon.practica_fct_android.domain.model.Bill
import com.aamagon.practica_fct_android.ui.view.screens.States
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class FilterBillsUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: Repository

    lateinit var filterBillsUseCase: FilterBillsUseCase
    val context = mockk<Context>(relaxed = true)

    val res  = listOf(Bill("Pagada", 20.0, "15/06/2004"))

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        filterBillsUseCase = FilterBillsUseCase(context, repository)
    }

    @Test
    fun `the repository returns the bills from database`() = runTest {
        // Given
        val example = listOf(
            Bill("Pagada", 24.50, "01/02/2004"),
            Bill("Pendiente de pago", 10.14, "30/06/2004"),
        )
        coEvery { repository.getAllBillsFromDatabase().bills } returns example

        // When
        val res = repository.getAllBillsFromDatabase().bills

        // Then
        assertEquals(example, res)
    }

    @Test
    fun `filters bills by price`() = runTest {
        // Given
        coEvery { context.getString(R.string.filPaid) } returns "Pagada"
        coEvery { context.getString(R.string.filCancel) } returns "Anulada"
        coEvery { context.getString(R.string.filFixed) } returns "Cuota fija"
        coEvery { context.getString(R.string.filWaiting) } returns "Pendiente de pago"
        coEvery { context.getString(R.string.filPlan) } returns "Plan de pago"

        coEvery { repository.getAllBillsFromDatabase().bills } returns res

        val states = States().apply {
            dateStringFrom.value = "01/01/2004"
            dateStringTo.value = "31/12/2004"
            sliderPos.floatValue = 20F
        }

        // When
        val result = filterBillsUseCase(states)

        // Then
        assertEquals(res, result)
    }

    @Test
    fun `filters bills by date range`() = runTest {
        // Given
        coEvery { context.getString(R.string.filPaid) } returns "Pagada"
        coEvery { context.getString(R.string.filCancel) } returns "Anulada"
        coEvery { context.getString(R.string.filFixed) } returns "Cuota fija"
        coEvery { context.getString(R.string.filWaiting) } returns "Pendiente de pago"
        coEvery { context.getString(R.string.filPlan) } returns "Plan de pago"

        coEvery { repository.getAllBillsFromDatabase().bills } returns res

        val states = States().apply {
            dateStringFrom.value = "01/01/2004"
            dateStringTo.value = "31/12/2004"
        }

        // When
        val result = filterBillsUseCase(states)

        // Then
        assertEquals(res, result)
    }

    @Test
    fun `filters bills by status`() = runTest {
        // Given
        coEvery { context.getString(R.string.filPaid) } returns "Pagada"
        coEvery { context.getString(R.string.filCancel) } returns "Anulada"
        coEvery { context.getString(R.string.filFixed) } returns "Cuota fija"
        coEvery { context.getString(R.string.filWaiting) } returns "Pendiente de pago"
        coEvery { context.getString(R.string.filPlan) } returns "Plan de pago"

        coEvery { repository.getAllBillsFromDatabase().bills } returns res

        val states = States().apply {
            dateStringFrom.value = "01/01/2004"
            dateStringTo.value = "31/12/2004"
            sliderPos.floatValue = 100F
            paidChecked.value = true
        }

        // When
        val result = filterBillsUseCase(states)

        // Then
        assertEquals(res, result)
    }
}