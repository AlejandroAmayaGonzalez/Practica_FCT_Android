package com.aamagon.practica_fct_android.core.extensions

import com.aamagon.practica_fct_android.data.database.entities.BillEntity
import com.aamagon.practica_fct_android.data.model.BillModel
import com.aamagon.practica_fct_android.data.model.BillsListModel
import com.aamagon.practica_fct_android.domain.model.Bill
import com.aamagon.practica_fct_android.domain.model.BillsList

fun BillModel.toDomain() = Bill(status, quantity, date)

fun BillsListModel.toDomain() = BillsList(numBills, bills.map { it.toDomain() })

fun BillEntity.toDomain() = Bill(status, quantity, date)

fun Bill.toDatabase(id: Int) = BillEntity(id = id, status = status, quantity = quantity, date = date)