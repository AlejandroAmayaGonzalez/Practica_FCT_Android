package com.aamagon.practica_fct_android.ui.view

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aamagon.practica_fct_android.ui.view.toolbar.MainToolBar
import com.aamagon.practica_fct_android.ui.view.toolbar.ToolbarRoutes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainApp(){

    val navController = rememberNavController()

    Scaffold (
        topBar = { MainToolBar(navController) },
        content = {
            NavHost(navController = navController, startDestination = ToolbarRoutes.HomeScreen.route){
                composable(route = ToolbarRoutes.HomeScreen.route) {
                    HomeScreen(navController)
                }
                composable(route = ToolbarRoutes.BillsScreen.route) {
                    BillsScreen(navController)
                }
                composable(route = ToolbarRoutes.SmartSolarScreen.route) {
                    SmartSolarScreen(navController)
                }
            }
        }
    )
}