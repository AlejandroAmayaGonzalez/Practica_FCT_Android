package com.aamagon.practica_fct_android.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aamagon.practica_fct_android.ui.view.toolbar.MainToolBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SmartSolarScreen(navController: NavController){
    Scaffold (
        topBar = { MainToolBar("SmartSolar", navController) },
        modifier = Modifier.fillMaxSize()
    ){ scafPad ->
        SmartSolarContent(modifier = Modifier.padding(top = 50.dp))
    }
}

@Composable
fun SmartSolarContent(modifier: Modifier = Modifier){
    Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "SmartSolar",
            fontSize = 50.sp
        )
    }
}

