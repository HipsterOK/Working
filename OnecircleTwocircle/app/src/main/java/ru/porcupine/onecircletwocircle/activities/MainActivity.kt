package ru.porcupine.onecircletwocircle.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import ru.porcupine.onecircletwocircle.controller.GameController
import ru.porcupine.onecircletwocircle.graphics.MainActivityUI
import ru.porcupine.onecircletwocircle.graphics.Template

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameController = GameController(this)
        gameController.startFall(this, lifecycleScope)

        setContent {
            Template().Background()
            MainActivityUI().Score(gameController)
            MainActivityUI().PlayerCircles(gameController = gameController)
            MainActivityUI().FallCircles(gameController = gameController)
            MainActivityUI().Controller(gameController = gameController)
        }
    }
}