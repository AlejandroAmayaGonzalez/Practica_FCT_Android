package com.aamagon.practica_fct_android.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aamagon.practica_fct_android.R
import com.aamagon.practica_fct_android.ui.theme.BillsToolbarBackground
import com.aamagon.practica_fct_android.ui.view.toolbar.MainToolBar

@Composable
fun BillsScreen(navController: NavController){
    Scaffold (
        topBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ){ scafPad ->
        BillContent(modifier = Modifier.padding(scafPad))
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun BillContent(modifier: Modifier = Modifier){

    Scaffold (
        topBar = { BillsToolbar() },
        modifier = modifier.padding()
    ){
        Text(
            text = "Prueba texto",
            modifier = modifier.padding()
        )
    }

    /*Column ( modifier = modifier.padding() ) {
        Row ( modifier = Modifier.fillMaxWidth().padding(16.dp) ){
            Text(
                text = stringResource(R.string.bills),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer( modifier = Modifier.weight(1f) )
            IconButton( onClick = {  } ) {
                Image(
                    painter = painterResource(R.drawable.icon_filter),
                    contentDescription = stringResource(R.string.contentDescFilter),
                    modifier = Modifier.height(40.dp).width(40.dp)
                )
            }
        }
    }*/
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillsToolbar(){
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.bills),
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors( containerColor = BillsToolbarBackground),
        actions = {
            IconButton( onClick = {  } ) {
                Image(
                    painter = painterResource(R.drawable.icon_filter),
                    contentDescription = stringResource(R.string.contentDescFilter),
                    modifier = Modifier.height(40.dp).width(40.dp)
                )
            }
        }
    )
}