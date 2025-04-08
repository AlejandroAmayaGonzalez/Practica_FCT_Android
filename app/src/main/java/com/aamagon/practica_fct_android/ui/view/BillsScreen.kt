package com.aamagon.practica_fct_android.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
fun BillsScreen(navController: NavController){
    Scaffold (
        topBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ){ scafPad ->
        BillContent(modifier = Modifier.padding(scafPad))
    }
}

@Preview(showBackground = true)
@Composable
fun BillContent(modifier: Modifier = Modifier){
    Column ( modifier = modifier.padding() ) {
        Text(
            text = "facturas",
            fontSize = 50.sp
        )
    }
}