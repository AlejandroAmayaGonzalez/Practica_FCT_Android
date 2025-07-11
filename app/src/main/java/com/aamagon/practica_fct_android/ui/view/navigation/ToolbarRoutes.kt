package com.aamagon.practica_fct_android.ui.view.navigation

sealed class ToolbarRoutes (val route: String ) {
    object HomeScreen: ToolbarRoutes("Home")
    object BillsScreen: ToolbarRoutes("Bills")
    object SmartSolarScreen: ToolbarRoutes("SmartSolar")
    object FilterBillsScreen: ToolbarRoutes("FilterBills")
}