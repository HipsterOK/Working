package ru.porcupine.cyberflight.ui.elements

import android.content.Context
import androidx.compose.runtime.Composable
import ru.porcupine.cyberflight.controllers.ActivityController
import ru.porcupine.cyberflight.controllers.GameController

class ResultUI(context: Context) {
    private val gameController = GameController(context)

    @Composable
    fun Play(context: Context) {
        Default().Button(
            gameController = gameController,
            text = "Play",
            h = 14f,
            w = 3f,
            font = 20f
        ) {
            ActivityController().activityMain(context)
        }
    }

    @Composable
    fun Menu(context: Context) {
        Default().Button(
            gameController = gameController,
            text = "Menu",
            h = 14f,
            w = 3f,
            font = 20f
        ) {
            ActivityController().activityHome(context)
        }
    }

    @Composable
    fun Score(score: Int) {
        Default().TemplateText(
            gameController = gameController,
            text = "Score: $score",
            h = 14f,
            w = 2f,
            font = 20f
        )
    }

    @Composable
    fun Record(record: Int) {
        Default().TemplateText(
            gameController = gameController,
            text = "Record: $record",
            h = 14f,
            w = 2f,
            font = 20f
        )
    }
}