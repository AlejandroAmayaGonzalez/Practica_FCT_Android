package com.aamagon.practica_fct_android.ui.view

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aamagon.practica_fct_android.ui.view.screens.BillsScreen
import com.aamagon.practica_fct_android.ui.view.screens.FilterBillsScreen
import com.aamagon.practica_fct_android.ui.view.screens.HomeScreen
import com.aamagon.practica_fct_android.ui.view.screens.SmartSolarScreen
import com.aamagon.practica_fct_android.ui.view.navigation.MainToolBar
import com.aamagon.practica_fct_android.ui.view.navigation.ToolbarRoutes
import com.aamagon.practica_fct_android.ui.viewmodel.BillsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainApp(
    billsViewModel: BillsViewModel = hiltViewModel(),
){

    val navController = rememberNavController()

    Scaffold (
        topBar = { MainToolBar(navController) },
        content = {
            NavHost(navController = navController, startDestination = ToolbarRoutes.HomeScreen.route){
                composable(route = ToolbarRoutes.HomeScreen.route) {
                    HomeScreen(navController, billsViewModel)
                }
                composable(route = ToolbarRoutes.BillsScreen.route) {
                    BillsScreen(navController, billsViewModel)
                }
                composable(route = ToolbarRoutes.SmartSolarScreen.route) {
                    SmartSolarScreen(navController)
                }
                composable(route = ToolbarRoutes.FilterBillsScreen.route) {
                    FilterBillsScreen(navController, billsViewModel)
                }
            }
        }
    )
}