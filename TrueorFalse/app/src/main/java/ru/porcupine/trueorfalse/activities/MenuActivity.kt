package ru.porcupine.trueorfalse.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.trueorfalse.Controller
import ru.porcupine.trueorfalse.R
import ru.porcupine.trueorfalse.SharedPreference

class MenuActivity : ComponentActivity() {

    var playerCount by mutableStateOf(1)
    private var visible by mutableStateOf(0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SharedPreference(this@MenuActivity).saveList("playersNames", null)
        SharedPreference(this@MenuActivity).saveList("score", null)

        val controller = Controller(this, application)

        setContent {
            Content(controller)
        }
    }

    @Composable
    fun Content(controller: Controller) {
        Background()
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Name(controller)
            Play(controller)
            PlayerCount(controller)
        }
        InfoView(controller)
    }

    @Composable
    private fun Name(controller: Controller) {
        Image(
            painter = painterResource(id = R.drawable.name),
            contentDescription = "name",
            modifier = Modifier
                .size((controller.screenWidth/1.5).dp)
        )
    }

    @Composable
    private fun Play(controller: Controller) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .absoluteOffset(x=(controller.screenWidth/11).dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier) {
                Image(
                    painter = painterResource(id = R.drawable.red_border),
                    contentDescription = "play border",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(
                            height = (controller.screenHeight / 11).dp,
                            width = (controller.screenWidth / 5).dp
                        )
                        .clickable {
                            val playerIntent = Intent(this@MenuActivity, PlayerNameActivity::class.java)
                            SharedPreference(this@MenuActivity).save("playerCount", playerCount)
                            startActivity(playerIntent)
                            finish()
                        },
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    text = "PLAY",
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                    fontSize = (controller.screenWidth / 20).sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            InfoButton(controller)
        }
    }

    @Composable
    private fun PlayerCount(controller: Controller) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.minis_icon),
                contentDescription = "minus",
                modifier = Modifier
                    .size((controller.screenWidth / 7).dp)
                    .clickable {
                        if (playerCount > 1) {
                            playerCount--
                        }
                    }
            )

            Box() {
                Image(
                    painter = painterResource(id = R.drawable.cyan_border),
                    contentDescription = "player border",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(
                            height = (controller.screenHeight / 11).dp,
                            width = (controller.screenWidth / 2).dp
                        ),
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    text = "AMOUNT OF PLAYERS\n$playerCount",
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                    fontSize = (controller.screenWidth / 20).sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.plus_icon),
                contentDescription = "plus",
                modifier = Modifier
                    .size((controller.screenWidth / 7).dp)
                    .clickable {
                        if (playerCount <35) {
                            playerCount++
                        }
                    }
            )
        }
    }

    @Composable
    private fun InfoButton(controller: Controller) {
        Box(modifier = Modifier.padding(start = (controller.screenWidth/20).dp)) {
            Image(
                painter = painterResource(id = R.drawable.orange_border),
                contentDescription = "info border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        (controller.screenHeight / 18).dp
                    )
                    .clickable {
                        visible = 1f
                    },
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "?",
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                fontSize = (controller.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    private fun InfoView(controller: Controller) {
        Box(
            Modifier
                .fillMaxSize()
                .alpha(visible)
                .background(Color.Black)){
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(25.dp)
                    .size(
                        height = (controller.screenHeight / 15).dp,
                        width = (controller.screenWidth / 5).dp
                    )
                    .align(Alignment.TopEnd)
                    .alpha(visible),
            ){
                Image(
                    painter = painterResource(id = R.drawable.red_border),
                    contentDescription = "",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            visible = 0f
                        }
                        .alpha(visible)
                )
                Text(
                    text = "Back",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .alpha(visible)
                )
            }
            Text(
                text = "Welcome!\n" +
                        "In the main menu, you can select the number of players participating in the game and press the \"Play\" button.\n" +
                        "Next, you can choose the names for the players (the default names are \"player 1\", \"player 2\", etc.).\n" +
                        "Then, in random order, the players are asked questions that they need to answer \"True\" or \"False\".\n" +
                        "At the end of the questions, or when someone scores 20 points, the game ends and the table is displayed.\n" +
                        "To restart the game by the same company, click \"Try Again\", and to add or change players, click \"Menu\"",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .alpha(visible)
                    .padding(20.dp)
//                    .background(Color.White)
            )
        }
    }

    @Composable
    private fun Background() {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}