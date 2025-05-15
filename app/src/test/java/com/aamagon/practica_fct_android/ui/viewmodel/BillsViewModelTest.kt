package com.aamagon.practica_fct_android.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aamagon.practica_fct_android.domain.FilterBillsUseCase
import com.aamagon.practica_fct_android.domain.GetBillsUseCase
import com.aamagon.practica_fct_android.domain.PrefsManagementUseCase
import com.aamagon.practica_fct_android.domain.model.Bill
import com.aamagon.practica_fct_android.ui.view.screens.States
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class BillsViewModelTest {

    @RelaxedMockK
    private lateinit var getBillsUseCase: GetBillsUseCase
    @RelaxedMockK
    private lateinit var filterBillsUseCase: FilterBillsUseCase
    @RelaxedMockK
    private lateinit var prefsManagementUseCase: PrefsManagementUseCase

    private lateinit var billsViewModel: BillsViewModel
    private val dispatcher = StandardTestDispatcher()

    val list = listOf(Bill("Pagada", 10.27, "28/10/2004"), Bill("Pagada", 10.27, "16/03/2015"))

    // Create a rule
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when the viewmodel is created, get all bills and set it`() = runTest {
        // Given
        coEvery { getBillsUseCase() } returns list

        // When
        billsViewModel = BillsViewModel(getBillsUseCase,
            filterBillsUseCase, prefsManagementUseCase)
        dispatcher.scheduler.advanceUntilIdle()

        // Then
        assert(billsViewModel.billsList.value == list)
    }

    @Test
    fun `when there are filters applied set the result`() = runTest {
        // Given
        coEvery { filterBillsUseCase(any()) } returns list

        billsViewModel = BillsViewModel(getBillsUseCase,
            filterBillsUseCase, prefsManagementUseCase)
        dispatcher.scheduler.advanceUntilIdle()

        // When
        billsViewModel.applyFilters(States())
        dispatcher.scheduler.advanceUntilIdle()

        // Then
        assert(billsViewModel.billsList.value == list)
    }

    @Test
    fun `when the filtered list is empty, set true noMatch`() = runTest {
        // Given
        coEvery { filterBillsUseCase(any()) } returns emptyList()

        billsViewModel = BillsViewModel(getBillsUseCase,
            filterBillsUseCase, prefsManagementUseCase)
        dispatcher.scheduler.advanceUntilIdle()

        // When
        billsViewModel.applyFilters(States())
        dispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(true, billsViewModel.noMatches.value)
    }

    @Test
    fun `reset the info with the first requested info`() = runTest {
        // Given
        coEvery { getBillsUseCase() } returns list

        billsViewModel = BillsViewModel(getBillsUseCase,
            filterBillsUseCase, prefsManagementUseCase)

        // When
        dispatcher.scheduler.advanceUntilIdle()
        billsViewModel.reset()

        // Then
        assert(billsViewModel.billsList.value == list)
    }

    @Test
    fun `change the shared preference value`() = runTest {
        // Given
        var bool = true
        coEvery { prefsManagementUseCase.setValuePref(bool) } answers {
            bool = firstArg()
        }
        coEvery { prefsManagementUseCase.getValuePref() } returns bool

        billsViewModel = BillsViewModel(getBillsUseCase,
            filterBillsUseCase, prefsManagementUseCase)

        // When
        billsViewModel.changeValuePref(true)

        // Then
        assertTrue(bool)
    }

    @Test
    fun `get the shared preference value`() = runTest {
        // Given
        val bool = true
        coEvery { prefsManagementUseCase.getValuePref() } returns bool

        billsViewModel = BillsViewModel(getBillsUseCase,
            filterBillsUseCase, prefsManagementUseCase)

        // When
        var res = billsViewModel.getValuePref()

        // Then
        assert(res == true)
    }

    @Test
    fun `reset noMatches value to default`(){
        billsViewModel = BillsViewModel(getBillsUseCase,
            filterBillsUseCase, prefsManagementUseCase)

        // Given
        val observer = Observer<Boolean> {}
        billsViewModel.noMatches.observeForever(observer)

        // When
        billsViewModel.resetNoMatchValue()

        // Then
        val result = billsViewModel.noMatches.value
        assertEquals(false, result)
    }
}