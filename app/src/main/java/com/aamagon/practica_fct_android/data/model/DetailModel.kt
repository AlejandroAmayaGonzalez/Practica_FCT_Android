package com.aamagon.practica_fct_android.data.model

import com.google.gson.annotations.SerializedName

data class DetailModel (
    @SerializedName("cau") val cau: String,
    @SerializedName("estado") val state: String,
    @SerializedName("tipo") val type: String,
    @SerializedName("compensacion") val compensation: String,
    @SerializedName("potencia") val power: String,
)