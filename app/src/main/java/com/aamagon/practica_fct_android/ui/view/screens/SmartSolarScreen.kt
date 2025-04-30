package com.aamagon.practica_fct_android.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aamagon.practica_fct_android.R
import com.aamagon.practica_fct_android.domain.model.Detail
import com.aamagon.practica_fct_android.ui.theme.DividerColor
import com.aamagon.practica_fct_android.ui.theme.White
import com.aamagon.practica_fct_android.ui.view.dialogs.InfoAboutStateDialog
import com.aamagon.practica_fct_android.ui.view.navigation.MainToolBar
import com.aamagon.practica_fct_android.ui.view.navigation.TabsRoutes
import com.aamagon.practica_fct_android.ui.viewmodel.DetailViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SmartSolarScreen(navController: NavController){
    Scaffold (
        topBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ){ scafPad ->
        SmartSolarContent(modifier = Modifier.padding(scafPad))
    }
}

@Composable
fun SmartSolarContent(modifier: Modifier = Modifier){

    Column ( modifier = modifier.padding() ) {

        Row ( modifier = Modifier.fillMaxWidth().padding(16.dp) ){
            Text(
                text = stringResource(R.string.smartSolar),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }

        SmartSolarTabs()
    }
}

@Composable
fun SmartSolarTabs(){

    val tabs = listOf(
        TabsRoutes.MyInstallation,
        TabsRoutes.Energy,
        TabsRoutes.Detail
    )

    // Allows navigation between tabs
    var pagerState = rememberPagerState(pageCount = {tabs.size})
    var scope = rememberCoroutineScope()

    Column {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabs.forEachIndexed { index,
                tab ->
                Tab(
                    text = { Text( text = tab.title ) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }

        // Starts the composable
        HorizontalPager(
            state = pagerState,
            beyondViewportPageCount = tabs.size
        ) { page ->
            tabs[page].screen()
        }
    }
}

@Composable
fun MyInstallation(){
    Column ( modifier = Modifier.padding(16.dp) ) {
        Text(
            text = stringResource(R.string.myInstallDesc)
        )

        Spacer( modifier = Modifier.height(10.dp) )

        Image(
            painter = painterResource(R.drawable.graph1),
            contentDescription = stringResource(R.string.contentDescGraph1),
            modifier = Modifier.height(300.dp).width(300.dp)
        )
    }
}

@Composable
fun Energy(){
    Column ( modifier = Modifier.padding(16.dp) ) {

        Image(
            painter = painterResource(R.drawable.management_plan),
            contentDescription = stringResource(R.string.contentDescGraph2),
            modifier = Modifier.height(300.dp).width(300.dp)
        )

        Spacer( modifier = Modifier.height(10.dp) )

        Text(
            text = stringResource(R.string.energyDesc)
        )
    }
}
@Preview
@Composable
fun Detail(detailViewModel: DetailViewModel = hiltViewModel()){

    var show = rememberSaveable { mutableStateOf(false) }
    // Access to detail fields
    val detail = detailViewModel.detail.observeAsState(Detail("","","","",""))

    Column ( modifier = Modifier.padding(16.dp) ) {
        TextField(
            value = detail.value.cau,
            onValueChange = {},
            label = { Text( text = stringResource(R.string.labelCAU) ) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = White,
                unfocusedContainerColor = White,
                focusedIndicatorColor = DividerColor
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer( modifier = Modifier.height(20.dp) )

        TextField(
            value = detail.value.state,
            onValueChange = {},
            label = { Text( text = stringResource(R.string.labelState) ) },
            trailingIcon = {
                IconButton(
                    onClick = {
                        show.value = true
                    }
                ){
                    Icon(
                        painter = painterResource(R.drawable.icon_info),
                        contentDescription = stringResource(R.string.contentDescInfo),
                        modifier = Modifier.height(25.dp).width(25.dp)
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = White,
                unfocusedContainerColor = White,
                focusedIndicatorColor = DividerColor
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer( modifier = Modifier.height(20.dp) )

        TextField(
            value = detail.value.type,
            onValueChange = {},
            label = { Text( text = stringResource(R.string.labelType) ) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = White,
                unfocusedContainerColor = White,
                focusedIndicatorColor = DividerColor
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer( modifier = Modifier.height(20.dp) )

        TextField(
            value = detail.value.compensation,
            onValueChange = {},
            label = { Text( text = stringResource(R.string.labelExcess) ) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = White,
                unfocusedContainerColor = White,
                focusedIndicatorColor = DividerColor
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer( modifier = Modifier.height(20.dp) )

        TextField(
            value = detail.value.power,
            onValueChange = {},
            label = { Text( text = stringResource(R.string.labelPower) ) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = White,
                unfocusedContainerColor = White,
                focusedIndicatorColor = DividerColor
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }

    if (show.value){
        InfoAboutStateDialog({show.value = false})
    }
}

