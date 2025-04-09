package com.aamagon.practica_fct_android.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.aamagon.practica_fct_android.ui.view.toolbar.FilterBillsToolbar
import com.aamagon.practica_fct_android.ui.view.toolbar.MainToolBar

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
    ){
        Text( text = "Filtrado" )
    }
}