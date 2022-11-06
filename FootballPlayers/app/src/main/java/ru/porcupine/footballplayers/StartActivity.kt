package ru.porcupine.footballplayers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val leagueConnect = LeagueConnect()
        leagueConnect.getLeagues("https://api-football-v1.p.rapidapi.com/v3/leagues?season=2020")
        val leagueDropDownMenuController = LeagueDropDownMenuController(leagueConnect)
        val seasonDropDownMenuController = SeasonDropDownMenuController()
        setContent {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Season(leagueConnect, leagueDropDownMenuController, seasonDropDownMenuController)
                League(leagueDropDownMenuController)
                Button(
                    onClick = {
                        val main = Intent(this@StartActivity, MainActivity::class.java)
                        main.putExtra("season", seasonDropDownMenuController.items[seasonDropDownMenuController.selectedIndex].toInt())

                        var find = 0L
                        leagueConnect.listLeagues.forEach {
                            if (it.league!!.name == leagueDropDownMenuController.items[leagueDropDownMenuController.selectedIndex]){
                                find = it.league.id!!
                            }
                        }
                        main.putExtra("league", find)
                        startActivity(main)
                        finish()
                    }
                ) {
                    Text(
                        text = "Search",
                        textAlign = TextAlign.Center,
                    )
                }
                Button(
                    onClick = {
                        val url = getString(R.string.url)
                        val builder = CustomTabsIntent.Builder()
                        val customTabsIntent = builder.build()
                        customTabsIntent.launchUrl(this@StartActivity, Uri.parse(url))
                    },
                ) {
                    Text(
                        text = "Privacy Policy",
                        textAlign = TextAlign.Center,
                    )
                }
            }
            LoadingData(leagueConnect)
        }
    }

    @Composable
    fun League(leagueDropDownMenuController: LeagueDropDownMenuController) {

        val icon = if (leagueDropDownMenuController.expanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

        Box(
            modifier = Modifier
                .padding(Controller(this@StartActivity).padding.dp)
        ) {

            Column(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .padding(all = 0.dp),
            ) {

                TextField(
                    readOnly = true,
                    value = leagueDropDownMenuController.items[leagueDropDownMenuController.selectedIndex],
                    onValueChange = { leagueDropDownMenuController.selectedIndex = leagueDropDownMenuController.items.indexOf(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            leagueDropDownMenuController.textFieldSize = coordinates.size.toSize()
                        },
                    label = {Text("League")},
                    trailingIcon = {
                        Icon(icon,"contentDescription",
                            Modifier.clickable { leagueDropDownMenuController.expanded = !leagueDropDownMenuController.expanded }
                        )
                    }
                )

                DropdownMenu(
                    expanded = leagueDropDownMenuController.expanded,
                    onDismissRequest = { leagueDropDownMenuController.expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current){leagueDropDownMenuController.textFieldSize.width.toDp()})
                ) {
                    leagueDropDownMenuController.items.forEachIndexed { index, s ->
                        DropdownMenuItem(onClick = {
                            leagueDropDownMenuController.selectedIndex = index
                            leagueDropDownMenuController.expanded = false
                        }) {
                            Text(text = s)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Season(leagueConnect: LeagueConnect, leagueDropDownMenuController: LeagueDropDownMenuController, seasonDropDownMenuController: SeasonDropDownMenuController) {
        var expanded by remember { mutableStateOf(false) }
        var textFieldSize by remember { mutableStateOf(Size.Zero)}
        val icon = if (expanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

        Box(
            modifier = Modifier
                .padding(Controller(this@StartActivity).padding.dp)
        ) {

            Column(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .padding(all = 0.dp),
            ) {

                TextField(
                    readOnly = true,
                    value = seasonDropDownMenuController.items[seasonDropDownMenuController.selectedIndex],
                    onValueChange = { seasonDropDownMenuController.selectedIndex = seasonDropDownMenuController.items.indexOf(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    label = {Text("League")},
                    trailingIcon = {
                        Icon(icon,"contentDescription",
                            Modifier.clickable { expanded = !expanded }
                        )
                    }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current){textFieldSize.width.toDp()})
                ) {
                    seasonDropDownMenuController.items.forEachIndexed { index, s ->
                        DropdownMenuItem(onClick = {
                            if(index!=seasonDropDownMenuController.selectedIndex) {
                                leagueConnect.getLeagues("https://api-football-v1.p.rapidapi.com/v3/leagues?season=$s")
                                leagueConnect.loading=1f
                                leagueDropDownMenuController.expanded = false
                                leagueDropDownMenuController.items = leagueConnect.listText
                                leagueDropDownMenuController.selectedIndex = 0
                                leagueDropDownMenuController.textFieldSize = Size.Zero
                                leagueConnect.loading=0f
                            }
                            seasonDropDownMenuController.selectedIndex = index
                            expanded = false
                        }) {
                            Text(text = s)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun LoadingData(leagueConnect: LeagueConnect) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(leagueConnect.loading)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Text(
                    text = "Loading"
                )
            }
        }
    }
}