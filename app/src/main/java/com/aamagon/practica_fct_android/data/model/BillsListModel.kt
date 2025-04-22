package com.aamagon.practica_fct_android.data.model

import com.google.gson.annotations.SerializedName

data class BillsListModel (
    @SerializedName("numFacturas") val numBills: Int,
    @SerializedName("facturas") val bills: List<BillModel>,
)