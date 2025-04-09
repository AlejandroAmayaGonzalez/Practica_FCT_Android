package com.aamagon.practica_fct_android.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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

@Preview(showBackground = true)
@Composable
fun BillContent(modifier: Modifier = Modifier){
    Column ( modifier = modifier.padding() ) {

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
    }
}