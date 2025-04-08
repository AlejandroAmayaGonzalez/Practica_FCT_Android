package com.aamagon.practica_fct_android.ui.view.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aamagon.practica_fct_android.R
import com.aamagon.practica_fct_android.ui.theme.ToolbarBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainToolBar(navController: NavController){
    TopAppBar(
        title = {
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
            Text(text = getTitle(currentRoute))
        },
        navigationIcon = {
            // Go back button
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(R.drawable.icon_arrow_back),
                    contentDescription = "Atrás"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors( containerColor = ToolbarBackground ),
        actions = {
            IconButton( onClick = { navController.navigate(ToolbarRoutes.BillsScreen.route) } ) {
                Image(
                    painter = painterResource(R.drawable.icon_bill),
                    contentDescription = "Facturas",
                    modifier = Modifier.height(24.dp).width(24.dp)
                )
            }
            IconButton( onClick = { navController.navigate(ToolbarRoutes.SmartSolarScreen.route) } ) {
                Image(
                    painter = painterResource(R.drawable.icon_smartsolar),
                    contentDescription = "Facturas",
                    modifier = Modifier.height(24.dp).width(24.dp)
                )
            }
        }
    )
}

// Change the toolbar title depending on the current section
fun getTitle(route: String?): String {
    return when(route){
        "Inicio" -> "Inicio"
        "Facturas" -> "Facturas"
        "SmartSolar" -> "SmartSolar"
        else -> "App"
    }
}
