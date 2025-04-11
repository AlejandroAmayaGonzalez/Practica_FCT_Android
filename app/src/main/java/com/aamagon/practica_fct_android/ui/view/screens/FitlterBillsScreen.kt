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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
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
import com.aamagon.practica_fct_android.ui.theme.LightGreen
import com.aamagon.practica_fct_android.ui.theme.MainToolbarBackground
import com.aamagon.practica_fct_android.ui.view.toolbar.FilterBillsToolbar
import com.aamagon.practica_fct_android.ui.view.toolbar.MainToolBar
import com.aamagon.practica_fct_android.ui.view.dialogs.DatePickerDialog
import kotlin.math.roundToInt

@Composable
fun FilterBillsScreen(navController: NavController){
    val states = States()

    Scaffold (
        topBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ){ scafPad ->
        FilterBillsContent(modifier = Modifier.padding(scafPad), navController, states)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FilterBillsContent(modifier: Modifier = Modifier, navController: NavController, states: States){
    Scaffold (
        topBar = { FilterBillsToolbar(navController) },
        modifier = modifier.padding()
    ){ scafPad ->
        Column (
            modifier = Modifier.padding(scafPad).fillMaxSize()
        ) {
            DateFilter(states)
            HorizontalDivider(
                color = DividerColor,
                thickness = 1.dp,
                modifier = Modifier.padding(top = 8.dp, start = 32.dp, end = 32.dp, bottom = 8.dp)
            )
            AmountFilter(states)
            HorizontalDivider(
                color = DividerColor,
                thickness = 1.dp,
                modifier = Modifier.padding(top = 8.dp, start = 32.dp, end = 32.dp, bottom = 8.dp)
            )
            CheckBoxFilter(states)
            HorizontalDivider(
                color = DividerColor,
                thickness = 1.dp,
                modifier = Modifier.padding(top = 8.dp, start = 32.dp, end = 32.dp, bottom = 8.dp)
            )
            FilterButtons(states)
        }
    }
}

@Composable
fun DateFilter(states: States){
    Box ( modifier = Modifier.fillMaxHeight(0.2F).padding(16.dp) ){
        Box ( modifier = Modifier.fillMaxWidth().align(Alignment.TopStart)) {
            Text(
                text = stringResource(R.string.titleDateFilter),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box ( modifier = Modifier.align(Alignment.BottomStart).padding(start = 64.dp) ) {
            DateCol( desc = stringResource(R.string.from), states.dateStringFrom, states.selectedDateTo, states.showFrom )
        }
        Box ( modifier = Modifier.align(Alignment.BottomEnd).padding(end = 64.dp) ) {
            DateCol( desc = stringResource(R.string.to), states.dateStringTo, states.selectedDateTo, states.showTo )
        }
    }
}

// The design of the component that contains date picker and text
@Composable
fun DateCol(desc: String, dateString: MutableState<String>, selectedDate: MutableState<Long?>, show: MutableState<Boolean>){
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
    Column ( modifier = Modifier.fillMaxHeight(0.2F).padding(16.dp) ) {
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

        Spacer( modifier = Modifier.padding(top = 8.dp) )

        Slider(
            value = states.sliderPos.floatValue,
            onValueChange = { states.sliderPos.floatValue = it },
            valueRange = 1f..300f,
            colors = SliderDefaults.colors(
                thumbColor = MainToolbarBackground,
                activeTickColor = MainToolbarBackground,
                inactiveTickColor = MainToolbarBackground,
                activeTrackColor = LightGreen,
                inactiveTrackColor = LightGreen
            ),
            thumb = {
                Image(
                    painter = painterResource(R.drawable.custom_thumb),
                    contentDescription = null
                )
            },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
    }
}

@Composable
fun CheckBoxFilter(states: States) {
    Column ( modifier = Modifier.fillMaxHeight(0.7F).padding(top = 8.dp, start = 16.dp) ) {
        Text(
            text = stringResource(R.string.titleCheckBoxFilter),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer( modifier = Modifier.padding(top = 8.dp) )

        Row ( verticalAlignment = Alignment.CenterVertically ) {
            Text( text = stringResource(R.string.paid) )
            Checkbox( checked = states.paidChecked.value, onCheckedChange = { states.paidChecked.value = it } )
        }
        Row ( verticalAlignment = Alignment.CenterVertically ) {
            Text( text = stringResource(R.string.cancelled) )
            Checkbox( checked = states.cancelledChecked.value, onCheckedChange = { states.cancelledChecked.value = it } )
        }
        Row ( verticalAlignment = Alignment.CenterVertically ) {
            Text( text = stringResource(R.string.fixedFee) )
            Checkbox( checked = states.fixedFeeChecked.value, onCheckedChange = { states.fixedFeeChecked.value = it } )
        }
        Row ( verticalAlignment = Alignment.CenterVertically ) {
            Text( text = stringResource(R.string.waitingForPayment) )
            Checkbox( checked = states.waitingChecked.value, onCheckedChange = { states.waitingChecked.value = it } )
        }
        Row ( verticalAlignment = Alignment.CenterVertically ) {
            Text( text = stringResource(R.string.paymentPlan) )
            Checkbox( checked = states.paymentPlanChecked.value, onCheckedChange = { states.paymentPlanChecked.value = it } )
        }
    }
}

@Composable
fun FilterButtons(states: States) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = LightGreen)
        ) {
            Text( text = stringResource(R.string.applyFilters) )
        }
        Button(
            onClick = { states.resetValues() },
            colors = ButtonDefaults.buttonColors(containerColor = DatePickerBackground)
        ) {
            Text( text = stringResource(R.string.deleteFilters) )
        }
    }
}

class States {
    // States for DatePicker
    var showFrom = mutableStateOf(false)
    var showTo = mutableStateOf(false)
    var selectedDateFrom = mutableStateOf<Long?>(null)
    var dateStringFrom = mutableStateOf("Día/Mes/Año")
    var selectedDateTo = mutableStateOf<Long?>(null)
    var dateStringTo = mutableStateOf("Día/Mes/Año")

    // State to control the Slider
    var sliderPos = mutableFloatStateOf(0F)

    // States for each CheckBox
    var paidChecked = mutableStateOf(false)
    var cancelledChecked = mutableStateOf(false)
    var fixedFeeChecked = mutableStateOf(false)
    var waitingChecked = mutableStateOf(false)
    var paymentPlanChecked = mutableStateOf(false)

    fun resetValues(){
        showFrom.value = false
        showTo.value = false
        selectedDateFrom.value = null
        dateStringFrom.value = "Día/Mes/Año"
        selectedDateTo.value = null
        dateStringTo.value = "Día/Mes/Año"

        sliderPos.floatValue = 0f

        paidChecked.value = false
        cancelledChecked.value = false
        fixedFeeChecked.value = false
        waitingChecked.value = false
        paymentPlanChecked.value = false
    }
}
