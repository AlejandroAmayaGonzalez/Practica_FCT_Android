package com.aamagon.practica_fct_android.ui.view.navigation

import androidx.compose.runtime.Composable
import com.aamagon.practica_fct_android.ui.view.screens.Energy
import com.aamagon.practica_fct_android.ui.view.screens.MyInstallation
import com.aamagon.practica_fct_android.ui.view.screens.Detail

sealed class TabsRoutes (val title: String, val screen: @Composable () -> Unit) {
    object MyInstallation: TabsRoutes(title = "Mi Instalación", screen = { MyInstallation() })
    object Energy: TabsRoutes(title = "Energía", screen = { Energy() })
    object Detail: TabsRoutes(title = "Detalles", screen = { Detail() })
}