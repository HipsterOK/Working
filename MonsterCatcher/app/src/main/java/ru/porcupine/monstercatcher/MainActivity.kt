package ru.porcupine.monstercatcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import ru.porcupine.monstercatcher.controller.GameController
import ru.porcupine.monstercatcher.screens.GameScreen
import ru.porcupine.monstercatcher.screens.MenuScreen
import ru.porcupine.monstercatcher.screens.ResultScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameController = GameController(this)
        gameController.game()
        gameController.game=false

        setContent {
            Background()
            GameScreen().GameContent(gameController)
            MenuScreen().MenuContent(gameController)
            ResultScreen().ResultContent(gameController)
        }
    }

    @Composable
    fun Background(){
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}