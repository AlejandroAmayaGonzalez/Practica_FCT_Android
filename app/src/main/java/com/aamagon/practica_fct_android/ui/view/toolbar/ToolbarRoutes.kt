package com.aamagon.practica_fct_android.ui.view.toolbar

sealed class ToolbarRoutes (val route: String ) {
    object HomeScreen: ToolbarRoutes("Home")
    object BillsScreen: ToolbarRoutes("Bills")
    object SmartSolarScreen: ToolbarRoutes("SmartSolar")
}