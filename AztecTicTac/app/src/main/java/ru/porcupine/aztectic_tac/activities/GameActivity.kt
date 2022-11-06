package ru.porcupine.aztectic_tac.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.porcupine.aztectic_tac.R
import ru.porcupine.aztectic_tac.controllers.GameController
import ru.porcupine.aztectic_tac.screens.GameScreen
import ru.porcupine.aztectic_tac.screens.Patterns

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameController = GameController(this)
        val gameScreen = GameScreen()

        setContent {
            Patterns().Background()
            gameScreen.AreaCrystal(gameController, this)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart){
                gameScreen.Turn(
                    image = R.drawable.character,
                    gameController,
                    0
                )
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd){
                gameScreen.Turn(
                    image = R.drawable.character1,
                    gameController,
                    1
                )
            }
            gameScreen.Win(gameController)
            gameScreen.Draw(gameController)
        }
    }
}