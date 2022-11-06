package ru.porcupine.aztectic_tac.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.porcupine.aztectic_tac.R
import ru.porcupine.aztectic_tac.controllers.GameController
import ru.porcupine.aztectic_tac.controllers.InfoController
import ru.porcupine.aztectic_tac.screens.MenuScreen
import ru.porcupine.aztectic_tac.screens.Patterns
import ru.porcupine.aztectic_tac.utils.SharedPreference

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameController = GameController(this)
        val infoController = InfoController(gameController)
        val record = SharedPreference(this).getValueInt("record")

        setContent {
            Patterns().Background()
            MenuScreen(this@MenuActivity).ButtonPolicy(this)
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                MenuScreen(this@MenuActivity).ButtonPlay(this@MenuActivity)
                MenuScreen(this@MenuActivity).ButtonInstruction(infoController)
                MenuScreen(this@MenuActivity).TextRecord(record = record)
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart){
                MenuScreen(this@MenuActivity).Character(
                    image = R.drawable.character,
                    gameController
                )
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd){
                MenuScreen(this@MenuActivity).Character(
                    image = R.drawable.character1,
                    gameController
                )
            }
            MenuScreen(this@MenuActivity).Info(
                gameController = gameController,
                infoController = infoController
            )
        }
    }
}