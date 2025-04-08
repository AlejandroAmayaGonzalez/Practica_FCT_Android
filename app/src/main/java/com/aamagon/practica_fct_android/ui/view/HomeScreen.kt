package com.aamagon.practica_fct_android.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aamagon.practica_fct_android.ui.view.toolbar.MainToolBar

@Composable
fun HomeScreen(navController: NavController){
    Scaffold (
        topBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize(),
        content = {scafPad ->
            HomeContent(modifier = Modifier.padding(scafPad))
        }
    )
}

@Composable
fun HomeContent(modifier: Modifier = Modifier){
    Column ( modifier = modifier.padding() ) {
        Text(
            text = "Inicio",
            fontSize = 100.sp
        )
    }
}