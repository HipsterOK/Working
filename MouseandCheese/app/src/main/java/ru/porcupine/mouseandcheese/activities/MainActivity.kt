package ru.porcupine.mouseandcheese.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.porcupine.mouseandcheese.controllers.GameController
import ru.porcupine.mouseandcheese.screens.Game
import ru.porcupine.mouseandcheese.screens.Info
import ru.porcupine.mouseandcheese.screens.Menu
import ru.porcupine.mouseandcheese.screens.Result

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameController = GameController(this)

        setContent {
            Game().GameContent(gameController = gameController, this)
            Result().ResultContent(gameController = gameController)
            Menu().MenuContent(gameController = gameController)
            Info().InfoContent(gameController = gameController)
        }
    }
}