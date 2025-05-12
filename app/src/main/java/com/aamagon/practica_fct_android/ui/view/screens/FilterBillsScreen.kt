package com.aamagon.practica_fct_android.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aamagon.practica_fct_android.R
import com.aamagon.practica_fct_android.ui.theme.Black
import com.aamagon.practica_fct_android.ui.theme.DatePickerBackground
import com.aamagon.practica_fct_android.ui.theme.DividerColor
import com.aamagon.practica_fct_android.ui.theme.Green
import com.aamagon.practica_fct_android.ui.theme.LightGreen
import com.aamagon.practica_fct_android.ui.theme.MainToolbarBackground
import com.aamagon.practica_fct_android.ui.view.navigation.FilterBillsToolbar
import com.aamagon.practica_fct_android.ui.view.navigation.MainToolBar
import com.aamagon.practica_fct_android.ui.view.dialogs.DatePickerDialog
import com.aamagon.practica_fct_android.ui.viewmodel.BillsViewModel
import kotlin.math.roundToInt

@Composable
fun FilterBillsScreen(navController: NavController, billsViewModel: BillsViewModel){
    Scaffold (
        topBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ){ scafPad ->
        FilterBillsContent(modifier = Modifier.padding(scafPad), navController, billsViewModel)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FilterBillsContent(modifier: Modifier = Modifier, navController: NavController, billsViewModel: BillsViewModel){

    val states = States()

    // Scroll State
    val scroll = rememberScrollState()
    LaunchedEffect(Unit) { scroll.animateScrollTo(100) }

    Scaffold (
        topBar = { FilterBillsToolbar(navController) },
        modifier = modifier.padding()
    ){ scafPad ->
        Column (
            modifier = Modifier.padding(scafPad).fillMaxSize()
                .verticalScroll(scroll)
        ) {
            DateFilter(states)
            Divider()
            AmountFilter(states)
            Divider()
            CheckBoxFilter(states)
            Divider()
            FilterButtons(states, billsViewModel, navController)
        }
    }
}

@Composable
fun DateFilter(states: States){
    Column ( modifier = Modifier.fillMaxSize().padding(16.dp) ){
        Text(
            text = stringResource(R.string.titleDateFilter),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row (
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            DateCol( desc = stringResource(R.string.from), states.dateStringFrom, states.selectedDateFrom, states.showFrom )
            DateCol( desc = stringResource(R.string.to), states.dateStringTo, states.selectedDateTo, states.showTo )
        }
    }
}

// The design of the component that contains date picker and text
@Composable
fun DateCol(desc: String, dateString: MutableState<String>,
            selectedDate: MutableState<Long?>,
            show: MutableState<Boolean>
){
    Column {
        Text( text = desc )
        Spacer( modifier = Modifier.height(5.dp) )
        TextButton( onClick = { show.value = true },
            colors = ButtonDefaults.buttonColors(
                contentColor = Black, containerColor = DatePickerBackground
            )
        ) {
            Text( text = dateString.value )
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
fun AmountFilter(states: States){
    Column ( modifier = Modifier.fillMaxHeight().padding(16.dp) ) {
        Text(
            text = stringResource(R.string.titleAmountFilter),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer( modifier = Modifier.padding(top = 8.dp) )

        Row( horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth() ) {
            Text( text = "1€" )
            Text(
                text = "1€ - ${states.sliderPos.floatValue.roundToInt()}€",
                color = LightGreen
            )
            Text( text = "300€" )
        }

        Slider(
            value = states.sliderPos.floatValue,
            onValueChange = { states.sliderPos.floatValue = it },
            valueRange = 1f..300f,
            colors = SliderDefaults.colors(
                activeTickColor = MainToolbarBackground,
                inactiveTickColor = MainToolbarBackground,
                activeTrackColor = LightGreen,
                inactiveTrackColor = Green
            ),
            thumb = {
                Image(
                    painter = painterResource(R.drawable.custom_thumb),
                    contentDescription = null,
                    modifier = Modifier.width(40.dp).height(40.dp)
                )
            },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
    }
}

@Composable
fun CheckBoxFilter(states: States) {
    Column ( modifier = Modifier.fillMaxHeight().padding(top = 8.dp, start = 16.dp) ) {
        Text(
            text = stringResource(R.string.titleCheckBoxFilter),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer( modifier = Modifier.padding(top = 8.dp) )

        CheckBoxRow(states.paidChecked, stringResource(R.string.paid))
        CheckBoxRow(states.cancelledChecked, stringResource(R.string.cancelled))
        CheckBoxRow(states.fixedFeeChecked, stringResource(R.string.fixedFee))
        CheckBoxRow(states.waitingChecked, stringResource(R.string.waitingForPayment))
        CheckBoxRow(states.paymentPlanChecked, stringResource(R.string.paymentPlan))
    }
}

@Composable
fun FilterButtons(states: States, billsViewModel: BillsViewModel, navController: NavController) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Button(
            onClick = {
                // Before apply filters checks if range is possible
                if (states.datesHaveACorrectRange()){
                    billsViewModel.applyFilters(states)
                    navController.popBackStack()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = LightGreen)
        ) {
            Text( text = stringResource(R.string.applyFilters) )
        }
        Button(
            onClick = {
                states.resetFilterValues()
                billsViewModel.reset()
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(containerColor = DatePickerBackground)
        ) {
            Text( text = stringResource(R.string.deleteFilters) )
        }
    }
}

@Composable
fun Divider(){
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ){
        HorizontalDivider(
            color = DividerColor,
            thickness = 1.dp,
            modifier = Modifier.padding(8.dp).fillMaxWidth(0.9F)
        )
    }
}

@Composable
fun CheckBoxRow(state: MutableState<Boolean>, type: String){
    Row ( verticalAlignment = Alignment.CenterVertically ) {
        Text( text = type )
        Checkbox(
            checked = state.value,
            onCheckedChange = { state.value = it },
            colors = CheckboxDefaults.colors( checkedColor = Green )
        )
    }
}
