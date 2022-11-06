package ru.porcupine.followme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import androidx.lifecycle.lifecycleScope
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameControl = GameController(this)
        gameControl.start(this, lifecycleScope)

        setContent {
            Content(gameControl)
        }
    }

    @Composable
    fun Content(gameControl: GameController) {
        Background()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, bottom = 40.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Count(gameControl)
                HP(gameControl)
            }
            TextTurn(gameControl)
            GameArea(gameControl)
        }
        SuccessIndicator(gameControl)
        WrongIndicator(gameControl)
    }

    @Composable
    fun HP(gameControl: GameController) {
        Image(
            painter = painterResource(id = gameControl.hpView),
            contentDescription = "",
            modifier = Modifier
                .size(
                    height = (gameControl.screenHeight / 9).dp,
                    width = (gameControl.screenWidth / 3).dp
                )
                .padding(5.dp)
        )
    }


    @Composable
    fun Count(gameControl: GameController) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(5.dp)
                .size(
                    height = (gameControl.screenHeight / 11).dp,
                    width = (gameControl.screenWidth / 3).dp
                )
        ){
            Image(
                painter = painterResource(id = R.drawable.border),
                contentDescription = "",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = "Score: ${gameControl.count} ",
                textAlign = TextAlign.Center,
                fontSize =18.sp,
                color = Color.Red,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.monoton_regular))),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun GameArea(gameControl: GameController) {
        var index = 0
        Box(Modifier.padding(top = 50.dp)) {
            Column {
                for (i in 0..3) {
                    Row {
                        for (j in 0..3) {
                            GameCard(gameControl, index)
                            index++
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun GameCard(gameControl:GameController, id: Int){
        Image(
            painter = painterResource(id = gameControl.cardListRes[id]),
            contentDescription = "",
            modifier = Modifier
                .alpha(gameControl.cardListVisible[id])
                .size(gameControl.btnSize.dp)
                .clickable {
                    if (gameControl.userPlay) {
                        gameControl.cardListVisible[id] = 1f
                        if (gameControl.quire[gameControl.playerPos] == id) {
                            gameControl.playerPos++
                        } else {
                            gameControl.userPlay = false
                            gameControl.turn = R.drawable.bot
                            gameControl.playerSuccess = false
                            gameControl.hp--
                            gameControl.hpView = gameControl.hpViewList[gameControl.hp]

                            gameControl.playerPos = 0
                            gameControl.wrongIndicator = 0.1f
                            Timer().schedule(timerTask {
                                gameControl.wrongIndicator = 0f
                            }, 1000)
                        }
                        if (gameControl.playerPos == gameControl.quireSize) {
                            gameControl.count++
                            gameControl.playerSuccess = true
                            gameControl.userPlay = false
                            gameControl.turn = R.drawable.bot
                            gameControl.quireSize++
                            gameControl.playerPos = 0
                            gameControl.successIndicator = 0.1f
                            Timer().schedule(timerTask {
                                gameControl.successIndicator = 0f
                            }, 500)
                        }
                        Timer().schedule(timerTask {
                            gameControl.cardListVisible[id] = 0.2f
                        }, 500)
                    }
                }
        )
    }

    @Composable
    fun SuccessIndicator(gameControl: GameController) {
        Box(
            Modifier
                .alpha(gameControl.successIndicator)
                .fillMaxSize()
                .background(Color.Green)
        )
    }

    @Composable
    fun WrongIndicator(gameControl: GameController) {
        Box(
            Modifier
                .alpha(gameControl.wrongIndicator)
                .fillMaxSize()
                .background(Color.Red)
        )
    }

    @Composable
    fun TextTurn(gameControl: GameController) {
        Image(
            painterResource(id = gameControl.turn),
            "",
            Modifier
                .size(
                    height = (gameControl.screenHeight / 8).dp,
                    width = (gameControl.screenWidth).dp
                )
                .padding(15.dp)
        )
    }

    @Composable
    fun Background() {
        Image(painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)
    }

}