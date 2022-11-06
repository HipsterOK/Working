package ru.porcupine.dartsvsballoons.screens

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
import ru.porcupine.dartsvsballoons.controller.GameController

class ResultScreen {
    @Composable
    fun ResultContent(gameController: GameController) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(gameController.screenController.resultPos.dp, 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                GameOver(gameController)
                PlayAgain(gameController)
                Menu(gameController)
                Score(gameController)
                Best(gameController)
            }
        }
    }

    @Composable
    fun Score(gameController: GameController){
        Text(
            text = "Score: ${gameController.score}",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.textSize).sp,
            color = Color.Black,
            modifier = Modifier
                .padding((gameController.constants.padding).dp)
        )
    }

    @Composable
    fun Best(gameController: GameController){
        Text(
            text = "Best: ${gameController.record}",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.textSize).sp,
            color = Color.Black,
            modifier = Modifier
                .padding((gameController.constants.padding).dp)
        )
    }

    @Composable
    fun PlayAgain(gameController: GameController){
        Card(
            Modifier
                .padding(gameController.constants.padding.dp)
                .clickable {
                    gameController.screenController.goGame(gameController)
                },
            elevation = (gameController.constants.screenWidth/30).dp,
            backgroundColor = Color.Yellow
        ) {
            Text(
                text = "Play again",
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
                fontSize = (gameController.constants.textSize).sp,
                color = Color.Black,
                modifier = Modifier
                    .padding((gameController.constants.padding).dp)
            )
        }
    }

    @Composable
    fun Menu(gameController: GameController){
        Card(
            Modifier
                .padding(gameController.constants.padding.dp)
                .clickable {
                    gameController.screenController.goMenu()
                },
            elevation = (gameController.constants.screenWidth/30).dp,
            backgroundColor = Color.Yellow
        ) {
            Text(
                text = "Main menu",
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
                fontSize = (gameController.constants.textSize).sp,
                color = Color.Black,
                modifier = Modifier
                    .padding((gameController.constants.padding).dp)
            )
        }
    }

    @Composable
    fun GameOver(gameController: GameController){
        Text(
            text = "Game over",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.textSize*2).sp,
            color = Color.Black,
            modifier = Modifier
                .padding((gameController.constants.padding).dp)
        )
    }
}