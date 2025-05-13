package com.aamagon.practica_fct_android.domain

import com.aamagon.practica_fct_android.data.Repository
import com.aamagon.practica_fct_android.domain.model.Detail
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetDetailUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: Repository

    lateinit var getDetailUseCase: GetDetailUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getDetailUseCase = GetDetailUseCase(repository)
    }

    @Test
    fun `return values from api`() = runBlocking {
        // Given
        val detail = Detail("test", "test", "test", "test", "test")
        coEvery { repository.getDetailFromApi() } returns detail

        // When
        val res = getDetailUseCase()

        // Then
        coVerify(exactly = 1) { repository.getDetailFromApi() }
        assert(res == detail)
    }

}