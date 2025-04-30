package com.aamagon.practica_fct_android.domain

import com.aamagon.practica_fct_android.data.Repository
import com.aamagon.practica_fct_android.domain.model.Detail
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Detail{
        return repository.getDetailFromApi()
    }
}