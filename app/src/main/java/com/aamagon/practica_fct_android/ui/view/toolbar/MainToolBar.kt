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
import androidx.compose.ui.res.stringResource
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
                    contentDescription = stringResource(R.string.contentDescBack)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors( containerColor = ToolbarBackground ),
        actions = {
            IconButton( onClick = { navController.navigate(ToolbarRoutes.HomeScreen.route) } ) {
                Image(
                    painter = painterResource(R.drawable.icon_home),
                    contentDescription = stringResource(R.string.contentDescHome),
                    modifier = Modifier.height(24.dp).width(24.dp)
                )
            }
            IconButton( onClick = { navController.navigate(ToolbarRoutes.BillsScreen.route) } ) {
                Image(
                    painter = painterResource(R.drawable.icon_bill),
                    contentDescription = stringResource(R.string.contentDescBills),
                    modifier = Modifier.height(24.dp).width(24.dp)
                )
            }
            IconButton( onClick = { navController.navigate(ToolbarRoutes.SmartSolarScreen.route) } ) {
                Image(
                    painter = painterResource(R.drawable.icon_smartsolar),
                    contentDescription = stringResource(R.string.contentDescSmart),
                    modifier = Modifier.height(24.dp).width(24.dp)
                )
            }
        }
    )
}

// Get the title depending on the section clicked
@Composable
fun getTitle(route: String?): String {
    return when(route){
        "Home" -> stringResource(R.string.homeRoute)
        "Bills" -> stringResource(R.string.billsRoute)
        "SmartSolar" -> stringResource(R.string.smartSolarRoute)
        else -> "App"
    }
}
