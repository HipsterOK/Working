package ru.porcupine.monstercatcher.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.monstercatcher.controller.GameController

class MenuScreen {
    @Composable
    fun MenuContent(gameController: GameController) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(gameController.screenController.menuPos.dp, 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Game(gameController)
                Info(gameController)
                Play(gameController)
                Best(gameController)
            }
        }
    }

    @Composable
    fun Best(gameController: GameController){
        Text(
            text = "Best: ${gameController.record}",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.textSize).sp,
            color = Color.White,
            modifier = Modifier
                .padding((gameController.constants.padding).dp)
                .fillMaxWidth()
        )
    }

    @Composable
    fun Play(gameController: GameController){
        Card(
            Modifier
                .padding(gameController.constants.padding.dp)
                .clickable {
                    gameController.screenController.goGame(gameController)
                },
            elevation = (gameController.constants.screenWidth/30).dp,
            backgroundColor = Color.Magenta
        ) {
            Text(
                text = "Play",
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
                fontSize = (gameController.constants.textSize).sp,
                color = Color.White,
                modifier = Modifier
                    .padding((gameController.constants.padding).dp)
            )

        }
    }

    @Composable
    fun Info(gameController: GameController){
        Text(
            text = "Don't let the monsters take over the planet, catch them all!",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.textSize/2).sp,
            color = Color.LightGray,
            modifier = Modifier
                .padding((gameController.constants.padding).dp)
        )
    }

    @Composable
    fun Game(gameController: GameController){
        Text(
            text = "Monster Catcher",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.textSize*2).sp,
            color = Color.White,
            modifier = Modifier
                .padding((gameController.constants.padding).dp)
                .fillMaxWidth()
        )
    }
}