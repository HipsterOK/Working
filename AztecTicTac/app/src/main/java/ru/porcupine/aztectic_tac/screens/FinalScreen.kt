package ru.porcupine.aztectic_tac.screens

import android.content.Context
import androidx.compose.runtime.Composable
import ru.porcupine.aztectic_tac.controllers.GameController
import ru.porcupine.aztectic_tac.utils.Intents

class FinalScreen(context: Context) {
    private val gameController = GameController(context)

    @Composable
    fun Play(context: Context) {
        Patterns().Button(
            gameController = gameController,
            text = "Play",
            h = 14f,
            w = 3f,
            font = 20f
        ) {
            Intents().game(context)
        }
    }

    @Composable
    fun Menu(context: Context) {
        Patterns().Button(
            gameController = gameController,
            text = "Menu",
            h = 14f,
            w = 3f,
            font = 20f
        ) {
            Intents().menu(context)
        }
    }

    @Composable
    fun Score(score: Int) {
        Patterns().Text(
            gameController = gameController,
            text = "Score: $score",
            h = 12f,
            w = 2f,
            font = 20f
        )
    }

    @Composable
    fun Record(record: Int) {
        Patterns().Text(
            gameController = gameController,
            text = "Record: $record",
            h = 12f,
            w = 2f,
            font = 20f
        )
    }
}
