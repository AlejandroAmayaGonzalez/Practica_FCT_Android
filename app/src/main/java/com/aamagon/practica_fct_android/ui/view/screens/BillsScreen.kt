package com.aamagon.practica_fct_android.ui.view.screens

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aamagon.practica_fct_android.R
import com.aamagon.practica_fct_android.domain.model.Bill
import com.aamagon.practica_fct_android.ui.view.dialogs.BillDialog
import com.aamagon.practica_fct_android.ui.view.navigation.BillsToolbar
import com.aamagon.practica_fct_android.ui.view.navigation.MainToolBar
import com.aamagon.practica_fct_android.ui.viewmodel.BillsViewModel

@Composable
fun BillsScreen(navController: NavController, billsViewModel: BillsViewModel){
    Scaffold (
        topBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ){ scafPad ->
        BillContent(modifier = Modifier.padding(scafPad), navController, billsViewModel)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BillContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    billsViewModel: BillsViewModel
){
    Scaffold (
        topBar = { BillsToolbar(navController) },
        modifier = modifier.padding()
    ){ scafPad ->
        BillsList(
            billsViewModel = billsViewModel,
            scafPad = scafPad
        )
    }
}

@Composable
fun BillsList(billsViewModel: BillsViewModel, scafPad: PaddingValues ){

    val billList = billsViewModel.billsList.observeAsState(emptyList())

    // To show Toasts Errors
    val context = LocalContext.current
    val errMsg = stringResource(R.string.toastErr)

    LazyColumn (
        modifier = Modifier.fillMaxSize()
            .padding(scafPad)
            .padding(16.dp)
    ){
        items (billList.value) { bill ->
            BillCard(
                bill = bill
            )
            Spacer( modifier = Modifier.height(8.dp) )
        }

        if (billList.value.isEmpty()){
            Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun BillCard(bill: Bill){

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

    BillDialog(show.value) { show.value = false }
}
