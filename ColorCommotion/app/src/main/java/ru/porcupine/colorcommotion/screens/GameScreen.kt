package ru.porcupine.colorcommotion.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.porcupine.colorcommotion.R
import ru.porcupine.colorcommotion.controllers.GameController

class GameScreen(private val context: Context) {
    @Composable
    fun GameContent(gameController: GameController){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(gameController.screenController.gamePos.dp,0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Score(gameController = gameController)
                    Time(gameController = gameController)
                }
                ColorsText(gameController = gameController)
                ColorsButton(gameController = gameController)
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
    fun Score(gameController: GameController){
        Patterns(context).TextPattern(text = "score\n${gameController.variables.score}", gameController = gameController)
    }

    @Composable
    fun Time(gameController: GameController){
        Patterns(context).TextPattern(text = "time\n${gameController.variables.time}", gameController = gameController)
    }

    @Composable
    fun ColorsText(gameController: GameController){
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(gameController.constants.padding.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in 0 until 3) {
                    Patterns(context).OneColorText(gameController = gameController, id = i)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(gameController.constants.padding.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in 3 until 6) {
                    Patterns(context).OneColorText(gameController = gameController, id = i)
                }
            }
        }
    }

    @Composable
    fun ColorsButton(gameController: GameController){
        Row {
            for (i in 0 until 6){
                Patterns(context).OneColorButton(gameController = gameController, id = i)
            }
        }
    }

}