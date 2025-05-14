package com.aamagon.practica_fct_android.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aamagon.practica_fct_android.domain.GetDetailUseCase
import com.aamagon.practica_fct_android.domain.model.Detail
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
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

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    @RelaxedMockK
    private lateinit var getDetailUseCase: GetDetailUseCase

    private lateinit var detailViewModel: DetailViewModel
    private val dispatcher = StandardTestDispatcher()

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
    fun `when the viewmodel is created, get the detail and set it`() = runTest {
        // Given
        val detail = Detail("test","test","test","test","test")
        coEvery { getDetailUseCase() } returns detail

        // When
        detailViewModel = DetailViewModel(getDetailUseCase)
        dispatcher.scheduler.advanceUntilIdle()

        // Then
        assert(detailViewModel.detail.value == detail)
    }
}