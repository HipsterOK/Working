package ru.porcupine.buildapedestal.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.porcupine.buildapedestal.other.Composablies
import ru.porcupine.buildapedestal.other.GameController
import ru.porcupine.buildapedestal.R
import ru.porcupine.buildapedestal.other.SharedPreference

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameController = GameController(this)

        setContent {
          MainContent(gameController)
        }
    }

    @Composable
    fun MainContent(gameController: GameController) {
        val score = intent.getIntExtra("score", 0)
        var record = SharedPreference(this).getValueInt("record")
        if(score > record){
            record = score
            SharedPreference(this).save("record", score)
        }
        Composablies().Background(back = R.drawable.back_1)

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
//            Image(
//                painter = painterResource(id = R.drawable.character),
//                contentDescription = "dama",
//                modifier = Modifier
//                    .size((gameController.screenHeight / 4).dp)
//            )
            Composablies().TemplateButton(gameController, "PLAY AGAIN", 11, 2, fun() {
                val gameIntent = Intent(this@ResultActivity, MainActivity::class.java)
                startActivity(gameIntent)
                finish()
            })
            Composablies().TemplateButton(gameController, "MAIN MENU", 11, 2, fun() {
                val gameIntent = Intent(this@ResultActivity, MenuActivity::class.java)
                startActivity(gameIntent)
                finish()
            })
            Composablies().TemplateTextField(gameController, "SCORE: $score", 11, 2.8)
            Composablies().TemplateTextField(gameController, "RECORD: $record", 11, 2.8)
        }
    }
}