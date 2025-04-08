package com.aamagon.practica_fct_android.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aamagon.practica_fct_android.ui.view.toolbar.MainToolBar

@Composable
fun HomeScreen(navController: NavController){
    Scaffold (
        topBar = { MainToolBar("Inicio", navController) },
        content = {scafPad ->
            HomeContent(modifier = Modifier.padding(scafPad))
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeContent(modifier: Modifier = Modifier){
    Column {
        Text(
            text = "Inicio",
            fontSize = 100.sp
        )
    }
}