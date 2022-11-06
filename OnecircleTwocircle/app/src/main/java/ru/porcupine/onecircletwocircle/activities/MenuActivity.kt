package ru.porcupine.onecircletwocircle.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.porcupine.onecircletwocircle.controller.GameController
import ru.porcupine.onecircletwocircle.controller.InfoController
import ru.porcupine.onecircletwocircle.functions.SharedPreference
import ru.porcupine.onecircletwocircle.graphics.HomeUI
import ru.porcupine.onecircletwocircle.graphics.InfoUI
import ru.porcupine.onecircletwocircle.graphics.Template

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val infoController = InfoController()
        val gameController = GameController(this)

        setContent {
            val record = SharedPreference(this).getValueInt("record")

            setContent {
                Template().Background()
                HomeUI(this@MenuActivity).Policy(this)
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                        HomeUI(this@MenuActivity).Play(this@MenuActivity)
                        HomeUI(this@MenuActivity).Info(infoController)
                    HomeUI(this@MenuActivity).BestRecord(record = record)
                }
                InfoUI().InfoView(gameController = gameController, infoController = infoController)
            }
        }
    }
}