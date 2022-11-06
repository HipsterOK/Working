package ru.porcupine.economyquiz.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.porcupine.economyquiz.controller.MainController
import ru.porcupine.economyquiz.elements.InfoElements

class InfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfoElements().InfoView(
                mainController = MainController(this),
                context = this
            )
        }
    }
}