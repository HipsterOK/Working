package ru.porcupine.everyonetosleep.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import ru.porcupine.everyonetosleep.Elements
import ru.porcupine.everyonetosleep.Logic
import ru.porcupine.everyonetosleep.R
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameLogic = Logic(this)
        gameLogic.fillWindow()
        Timer().schedule(timerTask {
            gameLogic.game(this@MainActivity, lifecycleScope)
        }, 1000)

        setContent {
            Elements().Background(image = R.drawable.background)
            Elements().ScoreAndTime(gameLogic = gameLogic)
            Elements().Buildings(gameLogic = gameLogic)
        }
    }
}