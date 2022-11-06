package ru.porcupine.colorcommotion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.porcupine.colorcommotion.controllers.GameController
import ru.porcupine.colorcommotion.screens.GameScreen
import ru.porcupine.colorcommotion.screens.MenuScreen
import ru.porcupine.colorcommotion.screens.Patterns
import ru.porcupine.colorcommotion.screens.ResultScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameController = GameController(this)
        gameController.initVar()

        setContent {
            Patterns(this).Background()
            GameScreen(this).GameContent(gameController = gameController)
            MenuScreen(this).MenuContent(gameController = gameController)
            ResultScreen(this).ResultContent(gameController = gameController)
        }
    }
}