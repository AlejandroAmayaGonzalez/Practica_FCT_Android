package com.aamagon.practica_fct_android.data.model

import com.google.gson.annotations.SerializedName

data class BillModel (
    @SerializedName("descEstado") val status: String,
    @SerializedName("importeOrdenacion") val quantity: Double,
    @SerializedName("fecha") val date: String
)