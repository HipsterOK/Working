package ru.porcupine.mysweets.screens

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.porcupine.mysweets.R
import ru.porcupine.mysweets.utils.Controller

class ResultScreen {
    @Composable
    fun ResultContent(controller: Controller) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(controller.screensController.resultPos.dp, 0.dp)
        ) {
            MenuScreen().Back()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Play(controller)
                Menu(controller)
                Score(controller)
                Best(controller)
                Player(controller)
            }
        }
    }
    @Composable
    fun Player(controller: Controller){
        Image(
            painter = painterResource(R.drawable.mouse_cheese),
            contentDescription = "",
            modifier = Modifier
                .size(controller.constants.playerSize.dp)
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
                text = "PLAY AGAIN",
                modifier = Modifier.padding(controller.constants.padding.dp),
                color = Color.Black,
                style = TextStyle(fontFamily = FontFamily(Font(controller.constants.font))),
            )
        }
    }

    @Composable
    fun Menu(controller: Controller){
        Card(
            elevation = 10.dp,
            backgroundColor = Color.Green,
            border = BorderStroke(1.dp,Color.Black),
            modifier = Modifier
                .clickable {
                    controller.screensController.menu()
                }
        ) {
            Text(
                text = "MAIN MENU",
                modifier = Modifier.padding(controller.constants.padding.dp),
                color = Color.Black,
                style = TextStyle(fontFamily = FontFamily(Font(controller.constants.font))),
            )
        }
    }

    @Composable
    fun Score(controller: Controller){
        Text(
            text = "Current score:\n${controller.variables.score}",
            textAlign = TextAlign.Center,
            color = Color.Black,
            style = TextStyle(fontFamily = FontFamily(Font(controller.constants.font))),
        )
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