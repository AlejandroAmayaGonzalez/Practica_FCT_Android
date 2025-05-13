package com.aamagon.practica_fct_android.domain

import com.aamagon.practica_fct_android.data.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class PrefsManagementUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: Repository

    lateinit var prefsManagementUseCase: PrefsManagementUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        prefsManagementUseCase = PrefsManagementUseCase(repository)
    }

    @Test
    fun `return the assigned value from shared preferences`() = runBlocking {
        // Given
        val bool = true
        coEvery { repository.getUseMock() } returns bool

        // When
        prefsManagementUseCase.getValuePref()

        // Then
        coVerify(exactly = 1) { repository.getUseMock() }
        assert(repository.getUseMock() == bool)
    }

    @Test
    fun `set values to shared preferences`() = runBlocking {
        // Given
        var bool = false

        // Simulate get and set
        coEvery { repository.setUseMock(any()) } answers {
            bool = firstArg()
        }
        coEvery { repository.getUseMock() } returns bool

        // When
        prefsManagementUseCase.setValuePref(true)

        // Then
        coVerify(exactly = 1) { repository.setUseMock(bool) }
        assertTrue(bool)
    }
}