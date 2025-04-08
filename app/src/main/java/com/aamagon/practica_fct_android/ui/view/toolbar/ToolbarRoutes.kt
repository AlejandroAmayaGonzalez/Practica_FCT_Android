package com.aamagon.practica_fct_android.ui.view.toolbar

sealed class ToolbarRoutes (val route: String ) {
    object HomeScreen: ToolbarRoutes("Inicio")
    object BillsScreen: ToolbarRoutes("Facturas")
    object SmartSolarScreen: ToolbarRoutes("SmartSolar")
}