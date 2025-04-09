package com.aamagon.practica_fct_android.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aamagon.practica_fct_android.R
import com.aamagon.practica_fct_android.ui.view.toolbar.MainToolBar

@Composable
fun HomeScreen(navController: NavController){
    Scaffold (
        topBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize(),
        content = {scafPad ->
            HomeContent(modifier = Modifier.padding(scafPad))
        }
    )
}

@Composable
fun HomeContent(modifier: Modifier = Modifier){
    Column ( modifier = modifier.padding() ) {
        Image(
            painter = painterResource(R.drawable.home_image),
            contentDescription = stringResource(R.string.contentDescLogoSmart),
            modifier = modifier.height(500.dp).width(500.dp)
        )
    }
}