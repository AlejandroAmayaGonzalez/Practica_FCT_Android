package com.aamagon.practica_fct_android.data.model

import com.google.gson.annotations.SerializedName

data class BillModel (
    @SerializedName("status") val status: String,
    @SerializedName("quantity") val quantity: Double,
    @SerializedName("date") val date: String
)