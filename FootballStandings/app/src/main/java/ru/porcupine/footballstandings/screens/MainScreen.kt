package ru.porcupine.footballstandings.screens

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import ru.porcupine.footballstandings.Filters
import ru.porcupine.footballstandings.MainViewModel
import ru.porcupine.footballstandings.R
import ru.porcupine.footballstandings.data.Constants
import ru.porcupine.footballstandings.fixtures.Response

class MainScreen {
    @Composable
    fun MainContent(mainViewModel: MainViewModel, constants: Constants, context: Context) {
        Back()
        PrivacyButton(context)
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Widget(mainViewModel = mainViewModel, constants)
            League(mainViewModel = mainViewModel)
            Team(mainViewModel)
        }
        
    }

    @Composable
    fun Back(){
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }

    @Composable
    fun PrivacyButton(context: Context){
            IconButton(onClick = {
                val url = context.getString(R.string.url)
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(context, Uri.parse(url))
            },
            Modifier.size((Constants(context).screenWidth/4).dp).padding((Constants(context).padding).dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "")
            }
    }

    @Composable
    fun Widget(mainViewModel: MainViewModel, constants: Constants){
        FixturesList(fixtures = mainViewModel.fixturesListResponse, constants, mainViewModel)
    }

    @Composable
    fun FixturesList(fixtures: List<Response>, constants: Constants, mainViewModel: MainViewModel) {
        LazyRow(Modifier.fillMaxWidth()) {
            itemsIndexed(items = fixtures) { index, item ->
                FixtureItem(fixture = item, constants, mainViewModel, index)
            }
        }
    }

    @Composable
    fun FixtureItem(
        fixture: Response,
        constants: Constants,
        mainViewModel: MainViewModel,
        index: Int
    ) {
        Card(
            modifier = Modifier
                .padding(constants.padding.dp)
                .width((constants.screenWidth - constants.padding * 2).dp)
                .height((constants.screenHeight / 2.8).dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Surface {

                Column(
                    Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(constants.padding.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = fixture.league?.logo).apply(block = fun ImageRequest.Builder.() {
                                    scale(Scale.FILL)
                                }).build()
                        ),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(constants.padding.dp)
                            .size((constants.screenHeight / 20).dp)
                    )
                    Text(
                        text = fixture.league!!.name!!,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(
                                        ImageRequest.Builder(
                                            LocalContext.current
                                        ).data(data = fixture.teams!!.home!!.logo).apply(block = fun ImageRequest.Builder.() {
                                            scale(Scale.FILL)
                                        }).build()
                                    ),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .padding(constants.padding.dp)
                                        .size((constants.screenHeight / 10).dp)
                                )
                                Text(
                                    text = fixture.teams.home!!.name.toString(),
                                    style = MaterialTheme.typography.body1,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Text(
                                text = mainViewModel.scoreText[index],
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier
                                    .background(
                                        Color.LightGray
                                    )
                                    .padding(constants.padding.dp)
                            )

                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(
                                        ImageRequest.Builder(
                                            LocalContext.current
                                        ).data(data = fixture.teams!!.away!!.logo).apply(block = fun ImageRequest.Builder.() {
                                            scale(Scale.FILL)
                                        }).build()
                                    ),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .padding(constants.padding.dp)
                                        .size((constants.screenHeight / 10).dp)
                                )
                                Text(
                                    text = fixture.teams.away!!.name.toString(),
                                    style = MaterialTheme.typography.body1,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                }
            }
        }
    }

    @Composable
    fun League(mainViewModel: MainViewModel){
        var mExpanded by remember { mutableStateOf(false) }

        var mSelectedText by remember { mutableStateOf("") }

        var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

        val icon = if (mExpanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

        Column(Modifier.padding(20.dp)) {

            TextField(
                readOnly = true,
                value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        mTextFieldSize = coordinates.size.toSize()
                    },
                label = {Text("League")},
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded }
                    )
                }
            )

            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
            ) {
                mainViewModel.filterLeagueList.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label
                        mExpanded = false
                        mainViewModel.findLeague = label
                        Filters().filterLeague(label, mainViewModel)
                    }) {
                        Text(text = label)
                    }
                }
            }
        }
    }

    @Composable
    fun Team(mainViewModel: MainViewModel){
        var mExpanded by remember { mutableStateOf(false) }

        var mSelectedText by remember { mutableStateOf("") }

        var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

        val icon = if (mExpanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

        Column(Modifier.padding(20.dp)) {

            TextField(
                readOnly = true,
                value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        mTextFieldSize = coordinates.size.toSize()
                    },
                label = {Text("Team")},
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded }
                    )
                }
            )

            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
            ) {
                mainViewModel.filterTeamList.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label
                        mExpanded = false
                        mainViewModel.findTeam = label
                        Filters().filterTeam(label, mainViewModel)
                    }) {
                        Text(text = label)
                    }
                }
            }
        }
    }
}