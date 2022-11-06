package ru.porcupine.mysweets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.porcupine.mysweets.screens.GameScreen
import ru.porcupine.mysweets.screens.InfoScreen
import ru.porcupine.mysweets.screens.MenuScreen
import ru.porcupine.mysweets.screens.ResultScreen
import ru.porcupine.mysweets.utils.Controller

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