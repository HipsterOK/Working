package ru.porcupine.rabbithole.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.porcupine.rabbithole.controllers.GameController
import ru.porcupine.rabbithole.screens.Game
import ru.porcupine.rabbithole.screens.Info
import ru.porcupine.rabbithole.screens.Menu
import ru.porcupine.rabbithole.screens.Result

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