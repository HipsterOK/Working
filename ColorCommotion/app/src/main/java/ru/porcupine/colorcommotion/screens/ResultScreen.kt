package ru.porcupine.colorcommotion.screens

import android.content.Context
import androidx.compose.foundation.layout.*
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
import ru.porcupine.colorcommotion.R
import ru.porcupine.colorcommotion.controllers.GameController

class ResultScreen(private val context: Context) {
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
                Patterns(context).ButtonPattern(gameController = gameController,
                    text = "PLAY again",
                ) {
                    gameController.screenController.goGame(gameController)
                }
                Patterns(context).ButtonPattern(gameController = gameController,
                    text = "Main Menu",
                ) {
                    gameController.screenController.goMenu()
                }
                Patterns(context).TextPattern(text = "Result: ${gameController.variables.score}", gameController = gameController)
                Patterns(context).TextPattern(text = "Best Result: ${gameController.variables.record}", gameController = gameController)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Patterns(context).Person(image = R.drawable.hero1, gameController)
                    Patterns(context).Person(image = R.drawable.hero2, gameController)
                }
            }
        }
    }

    @Composable
    fun GameOver(gameController: GameController){
        Text(
            text = "Game over",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.textSize*2).sp,
            color = Color.White,
            modifier = Modifier
                .padding((gameController.constants.padding).dp)
        )
    }
}