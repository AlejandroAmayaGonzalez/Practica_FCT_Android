package com.aamagon.practica_fct_android.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aamagon.practica_fct_android.R
import com.aamagon.practica_fct_android.ui.theme.DividerColor
import com.aamagon.practica_fct_android.ui.view.toolbar.FilterBillsToolbar
import com.aamagon.practica_fct_android.ui.view.toolbar.MainToolBar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun FilterBillsScreen(navController: NavController){
    Scaffold (
        topBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ){ scafPad ->
        FilterBillsContent(modifier = Modifier.padding(scafPad), navController)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FilterBillsContent(modifier: Modifier = Modifier, navController: NavController){
    Scaffold (
        topBar = { FilterBillsToolbar(navController) },
        modifier = modifier.padding()
    ){ scafPad ->
        Column (
            modifier = Modifier.padding(scafPad).fillMaxSize()
        ) {
            DateFilter()
            HorizontalDivider(
                color = DividerColor,
                thickness = 1.dp,
                modifier = Modifier.padding(top = 16.dp, start = 32.dp, end = 32.dp)
            )
        }
    }
}

@Composable
fun DateFilter(){
    Box ( modifier = Modifier.fillMaxHeight(0.2F).padding(16.dp) ){
        Box ( modifier = Modifier.fillMaxWidth().align(Alignment.TopStart)) {
            Text( text = stringResource(R.string.titleDateFilter), fontSize = 20.sp, fontWeight = FontWeight.Bold )
        }
        Box ( modifier = Modifier.align(Alignment.BottomStart).padding(start = 64.dp) ) {
            DateCol( desc = stringResource(R.string.from) )
        }
        Box ( modifier = Modifier.align(Alignment.BottomEnd).padding(end = 64.dp) ) {
            DateCol( desc = stringResource(R.string.to) )
        }
    }
}

// The design of the component that contains date picker and text
@Composable
fun DateCol(desc: String){

    var show = rememberSaveable { mutableStateOf(false) }
    var selectedDate = rememberSaveable { mutableStateOf<Long?>(null) }
    var dateString = rememberSaveable { mutableStateOf("Día/Mes/Año") }

    Column {
        Text( text = desc )
        Spacer( modifier = Modifier.height(8.dp) )
        TextButton( onClick = { show.value = true } ) {
            Text(
                text = dateString.value
            )
        }
    }

    if (show.value){
        DatePickerDialog(
            dateString = dateString,
            onDateSelected = {
                selectedDate.value = it
                show.value = false
            },
            onDismiss = { show.value = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    dateString: MutableState<String>,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
){
    val datePickerState = rememberDatePickerState()
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton( onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss
                dateString.value = dateFormat.format(Date(datePickerState.selectedDateMillis!!)).toString()
            } ) {
                Text( text = stringResource(R.string.acceptDialog) )
            }
        },
        dismissButton = {
            TextButton( onClick = { onDismiss } ) {
                Text( text = stringResource(R.string.cancelDialog) )
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}