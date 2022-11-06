package ru.porcupine.aztectic_tac.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.porcupine.aztectic_tac.R
import ru.porcupine.aztectic_tac.controllers.GameController
import ru.porcupine.aztectic_tac.screens.FinalScreen
import ru.porcupine.aztectic_tac.screens.MenuScreen
import ru.porcupine.aztectic_tac.screens.Patterns
import ru.porcupine.aztectic_tac.utils.SharedPreference

class FinalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var record = SharedPreference(this).getValueInt("record")
            val score = intent.getIntExtra("score",0)
            if(score>record){
                record = score
                SharedPreference(this).save("record", record)
            }

            setContent {
                Patterns().Background()
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    FinalScreen(this@FinalActivity).Play(this@FinalActivity)
                    FinalScreen(this@FinalActivity).Menu(this@FinalActivity)
                    FinalScreen(this@FinalActivity).Score(score = score)
                    FinalScreen(this@FinalActivity).Record(record = record)
                }
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart){
                    MenuScreen(this@FinalActivity).Character(
                        image = R.drawable.character,
                        GameController(this@FinalActivity)
                    )
                }
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd){
                    MenuScreen(this@FinalActivity).Character(
                        image = R.drawable.character1,
                        GameController(this@FinalActivity)
                    )
                }
            }
        }
    }
}