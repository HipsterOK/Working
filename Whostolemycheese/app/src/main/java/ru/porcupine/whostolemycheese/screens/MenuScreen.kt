package ru.porcupine.whostolemycheese.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.porcupine.whostolemycheese.R
import ru.porcupine.whostolemycheese.utils.Controller

class MenuScreen {
    @Composable
    fun MenuContent(controller: Controller) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(controller.screensController.menuPos.dp, 0.dp)
        ) {
            Back()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Play(controller)
                Info(controller)
                Best(controller)
                GameScreen().Player(controller)
            }
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
    fun Play(controller: Controller){
        Card(
            elevation = 10.dp,
            backgroundColor = colorResource(id = R.color.purple_200),
            border = BorderStroke(1.dp,Color.Black),
            modifier = Modifier
                .clickable {
                    controller.variables.score=0
                    controller.variables.gameEnable = true
                    controller.enemy.enemyOut()
                    controller.screensController.game()
                    controller.runThread()
                }
        ) {
            Text(
                text = "PLAY",
                modifier = Modifier.padding(controller.constants.padding.dp),
                color = Color.Black,
                style = TextStyle(fontFamily = FontFamily(Font(controller.constants.font))),
            )
        }
    }

    @Composable
    fun Info(controller: Controller){
        Card(
            elevation = 10.dp,
            backgroundColor = Color.Green,
            border = BorderStroke(1.dp,Color.Black),
            modifier = Modifier
                .clickable {
                    controller.screensController.info()
                }
        ) {
            Text(
                text = "INFO",
                modifier = Modifier.padding(controller.constants.padding.dp),
                color = Color.Black,
                style = TextStyle(fontFamily = FontFamily(Font(controller.constants.font))),
            )
        }
    }

    @Composable
    fun Best(controller: Controller){
        Text(
            text = "Best result:\n${controller.variables.record}",
            textAlign = TextAlign.Center,
            color = Color.Black,
            style = TextStyle(fontFamily = FontFamily(Font(controller.constants.font))),
        )
    }
}