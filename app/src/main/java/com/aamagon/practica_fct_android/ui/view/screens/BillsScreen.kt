package com.aamagon.practica_fct_android.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aamagon.practica_fct_android.R
import com.aamagon.practica_fct_android.data.model.BillModel
import com.aamagon.practica_fct_android.ui.view.toolbar.BillsToolbar
import com.aamagon.practica_fct_android.ui.view.toolbar.MainToolBar

@Composable
fun BillsScreen(navController: NavController){
    Scaffold (
        topBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ){ scafPad ->
        BillContent(modifier = Modifier.padding(scafPad), navController)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BillContent(modifier: Modifier = Modifier, navController: NavController){

    Scaffold (
        topBar = { BillsToolbar(navController) },
        modifier = modifier.padding()
    ){ scafPad ->
        BillsList(
            list = listOf(
                BillModel("Pendiente de pago", 69.99, "28 Mar 2020"),
                BillModel("", 45.55, "10 Jul 2025"),
                BillModel("Pagada", 120.96, "6 Dec 2024"),
                BillModel("Pendiente de pago", 69.99, "28 Mar 2020"),
                BillModel("", 45.55, "10 Jul 2025"),
                BillModel("Pagada", 120.96, "6 Dec 2024"),
                BillModel("Pendiente de pago", 69.99, "28 Mar 2020"),
                BillModel("", 45.55, "10 Jul 2025"),
                BillModel("Pagada", 120.96, "6 Dec 2024")
            ),
            scafPad = scafPad
        )
    }
}

@Composable
fun BillsList(list: List<BillModel>, scafPad: PaddingValues ){
    LazyColumn (
        modifier = Modifier.fillMaxSize()
            .padding(scafPad)
            .padding(16.dp)
    ){
        items (list) { bill ->
            BillCard(
                bill = bill
            )
            Spacer( modifier = Modifier.height(8.dp) )
        }
    }
}

@Composable
fun BillCard(bill: BillModel){

    // Variable to control when the dialog is shown
    var show = rememberSaveable { mutableStateOf(false) }
    val priceBill = bill.quantity.toString()

    Card (
        modifier = Modifier.height(100.dp)
            .clickable(onClick = { show.value = true })
    ) {
        Box ( modifier = Modifier.fillMaxWidth().padding(16.dp) ){
            Box (
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Column ( modifier = Modifier.align(Alignment.Center) ) {
                    Text(
                        text = bill.date,
                        fontSize = 30.sp
                    )
                    Text(
                        text = bill.status,
                        fontSize = 20.sp
                    )
                }
            }

            Box (
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Row ( modifier = Modifier.align(Alignment.Center) ) {
                    Text(
                        text = "$priceBill €",
                        fontSize = 30.sp
                    )

                    Spacer( modifier = Modifier.width(10.dp) )

                    Image(
                        painter = painterResource(R.drawable.icon_arrow_about),
                        contentDescription = stringResource(R.string.contentDescDetail),
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
        }
    }

    BillDialog(show.value, {show.value = false})
}

@Composable
fun BillDialog(show: Boolean, onDismiss: () -> Unit){
    if (show){
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {},
            dismissButton = {
                TextButton( onClick = { onDismiss() } ) {
                    Text(
                        text = stringResource(R.string.dismissButton),
                        fontSize = 20.sp
                    )
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.titleDialog),
                    fontSize = 30.sp
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.bodyDialog),
                    fontSize = 20.sp
                )
            }
        )
    }
}