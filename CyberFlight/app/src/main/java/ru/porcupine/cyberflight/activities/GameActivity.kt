package ru.porcupine.cyberflight.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import ru.porcupine.cyberflight.controllers.GameController
import ru.porcupine.cyberflight.threads.GameThread
import ru.porcupine.cyberflight.ui.elements.Default
import ru.porcupine.cyberflight.ui.elements.GameActivityUI

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gameActivityUI = GameActivityUI()
        val gameController = GameController(this)
        GameThread().startThreadMove(lifecycleScope, gameController)
        GameThread().startThreadMeteor(this, lifecycleScope, gameController)
        GameThread().startThreadScore(lifecycleScope, gameController)

        setContent {
            Default().Background(this@GameActivity)
            gameActivityUI.Score(gameController)
            gameActivityUI.PlayerPlane(gameController)
            gameActivityUI.Controller(gameController)
            gameActivityUI.Enemies(gameController)
        }
    }
}