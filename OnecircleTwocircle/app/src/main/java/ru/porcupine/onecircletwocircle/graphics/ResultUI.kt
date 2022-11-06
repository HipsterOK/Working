package ru.porcupine.onecircletwocircle.graphics

import android.content.Context
import androidx.compose.runtime.Composable
import ru.porcupine.onecircletwocircle.controller.GameController
import ru.porcupine.onecircletwocircle.functions.ChangeActivity

class ResultUI(context: Context) {

    private val gameController = GameController(context)

    @Composable
    fun Play(context: Context) {
        Template().Button(
            gameController = gameController,
            text = "Play",
            h = 14f,
            w = 3f,
            font = 20f
        ) {
            ChangeActivity().newIntentGame(context)
        }
    }

    @Composable
    fun Menu(context: Context) {
        Template().Button(
            gameController = gameController,
            text = "Menu",
            h = 14f,
            w = 3f,
            font = 20f
        ) {
            ChangeActivity().newIntentHome(context)
        }
    }

    @Composable
    fun Score(score: Int) {
        Template().TemplateText(
            gameController = gameController,
            text = "Score: $score",
            h = 14f,
            w = 3f,
            font = 20f
        )
    }

    @Composable
    fun BestRecord(record: Int) {
        Template().TemplateText(
            gameController = gameController,
            text = "Record: $record",
            h = 14f,
            w = 3f,
            font = 20f
        )
    }
}
