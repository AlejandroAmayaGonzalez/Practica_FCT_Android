package com.aamagon.practica_fct_android.ui.view.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aamagon.practica_fct_android.R
import com.aamagon.practica_fct_android.ui.view.navigation.MainToolBar
import com.aamagon.practica_fct_android.ui.viewmodel.BillsViewModel

@Composable
fun HomeScreen(navController: NavController, billsViewModel: BillsViewModel){
    Scaffold (
        topBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize(),
        content = {scafPad ->
            HomeContent(modifier = Modifier.padding(scafPad), billsViewModel)
        }
    )
}

@Composable
fun HomeContent(modifier: Modifier = Modifier, billsViewModel: BillsViewModel){
    // Able to save the switch value when the user changes the view
    var checked = rememberSaveable { mutableStateOf(billsViewModel.getValuePref()) }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding()
    ) {
        Image(
            painter = painterResource(R.drawable.home_image),
            contentDescription = stringResource(R.string.contentDescLogoSmart),
            modifier = modifier.height(500.dp).width(500.dp)
        )

        Switch(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        )

        // Show a toast to tell if the mocks are enabled or disabled
        if (checked.value){
            Toast.makeText(LocalContext.current, stringResource(R.string.enable), Toast.LENGTH_SHORT).show()
            billsViewModel.changeValuePref(checked.value)
        }else{
            Toast.makeText(LocalContext.current, stringResource(R.string.disable), Toast.LENGTH_SHORT).show()
            billsViewModel.changeValuePref(checked.value)
        }
    }
}