package ru.porcupine.footballplayers

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val controller = Controller(this)
        controller.getPlayers(intent.getLongExtra("league", 0), intent.getIntExtra("season", 2020))

        setContent {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Widget(controller)
            OpenPlayer(controller)
            NoInfo(controller)
            LoadingData(controller)
        }

    }

    @Composable
    fun Widget(controller: Controller) {
        FixturesList(controller)
    }

    @Composable
    fun FixturesList(controller: Controller) {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .offset(controller.lazyPos.dp, 0.dp)) {
            itemsIndexed(items = controller.connect.listPlayers) { index, item ->
                PlayerCard(response = item, controller, index)
            }
        }
    }

    @Composable
    fun PlayerCard(response: ru.porcupine.footballplayers.models.player.Response, controller: Controller, id: Int) {
        Card(
            elevation = 10.dp,
            modifier = Modifier
                .padding(controller.padding.dp)
                .clickable {
                    controller.openIdCard = id
                    controller.fillDataCard()
                    controller.cardPos = 0f
                    controller.lazyPos = controller.screenWidth
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(
                            LocalContext.current
                        ).data(data = response.player!!.photo)
                            .apply(block = fun ImageRequest.Builder.() {
                                scale(Scale.FILL)
                            }).build()
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(controller.padding.dp)
                        .size((controller.screenHeight / 8).dp)
                )
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        text = response.player.name.toString(),
                        modifier = Modifier
                            .padding(controller.padding.dp)
                    )
                    val rating: String =
                        if (response.statistics!![0].games!!.rating.toString() == "null") {
                            "No information available"
                        } else response.statistics[0].games!!.rating.toString()
                    Text(
                        text = rating,
                        modifier = Modifier
                            .padding(controller.padding.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun LoadingData(controller: Controller) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(controller.connect.loading)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
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

    @Composable
    fun OpenPlayer(controller: Controller) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(controller.cardPos.dp, 0.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(controller.padding.dp)
            ) {
                InfoPlayerCard(controller)
                Row {
                    HeightCard(controller)
                    WeightCard(controller)
                    AgeCard(controller)
                }
            }
        }
    }

    @Composable
    fun InfoPlayerCard(controller: Controller) {
        Button(
            onClick = {
                controller.cardPos = controller.screenWidth
                controller.lazyPos = 0f
            }) {
            Text(
                text = "Back",
                textAlign = TextAlign.Center,
            )
        }
        Card(
            elevation = 10.dp,
            modifier = Modifier
                .padding(controller.padding.dp)
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(controller.padding.dp)
            ) {
                Text(
                    text = controller.position.toString(),
                    textAlign = TextAlign.Center,
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Goals: ${controller.goal}",
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Assists: ${controller.assists}",
                            textAlign = TextAlign.Center,
                        )
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(
                                    LocalContext.current
                                ).data(data = controller.teamPhoto)
                                    .apply(block = fun ImageRequest.Builder.() {
                                        scale(Scale.FILL)
                                    }).build()
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(controller.padding.dp)
                                .size((controller.screenHeight / 8).dp)
                        )
                        Text(
                            text = controller.teamName.toString(),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(
                                    LocalContext.current
                                ).data(data = controller.playerPhoto)
                                    .apply(block = fun ImageRequest.Builder.() {
                                        scale(Scale.FILL)
                                    }).build()
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(controller.padding.dp)
                                .size((controller.screenHeight / 8).dp)
                        )
                        Text(
                            text = controller.firstName.toString(),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = controller.lastName.toString(),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
    
    @Composable
    fun WeightCard(controller: Controller){
        Card(
            elevation = 10.dp,
            modifier = Modifier
                .padding(controller.padding.dp)
                .fillMaxWidth(0.5f)
        ) {
            Text(
                text = "Weight\n${controller.playerWeight}",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(controller.padding.dp)
            )
        }
    }

    @Composable
    fun HeightCard(controller: Controller){
        Card(
            elevation = 10.dp,
            modifier = Modifier
                .padding(controller.padding.dp)
                .fillMaxWidth(0.3f)
        ) {
            Text(
                text = "Height\n${controller.playerHeight}",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(controller.padding.dp)
            )
        }
    }

    @Composable
    fun AgeCard(controller: Controller){
        Card(
            elevation = 10.dp,
            modifier = Modifier
                .padding(controller.padding.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Age\n${controller.playerAge}",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(controller.padding.dp)
            )
        }
    }
    
    @Composable
    fun NoInfo(controller: Controller){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(controller.connect.noInfo)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "There is no information about the players"
            )
        }
    }

    override fun onBackPressed() {
        val menu = Intent(this@MainActivity, StartActivity::class.java)
        startActivity(menu)
        finish()
    }
}