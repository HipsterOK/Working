package ru.porcupine.whostolemycheese

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.porcupine.whostolemycheese.screens.GameScreen
import ru.porcupine.whostolemycheese.screens.InfoScreen
import ru.porcupine.whostolemycheese.screens.MenuScreen
import ru.porcupine.whostolemycheese.screens.ResultScreen
import ru.porcupine.whostolemycheese.utils.Controller

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val controller = Controller(this)

        setContent {
            GameScreen().GameContent(controller = controller)
            MenuScreen().MenuContent(controller = controller)
            ResultScreen().ResultContent(controller = controller)
            InfoScreen().InfoContent(controller = controller)
        }
    }
}