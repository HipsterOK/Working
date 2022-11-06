package ru.porcupine.dodgethelightning.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.porcupine.dodgethelightning.Controller
import ru.porcupine.dodgethelightning.R
import ru.porcupine.dodgethelightning.SharedPreference
import ru.porcupine.dodgethelightning.Templates

class FinalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val controller = Controller(this)

        setContent {
            MainContent(controller)
        }
    }

    @Composable
    fun MainContent(controller: Controller) {
        val score = intent.getIntExtra("score", 0)
        var record = SharedPreference(this).getValueInt("recordDodgeTheLightning")
        if(score > record){
            record = score
            SharedPreference(this).save("recordDodgeTheLightning", score)
        }
        Templates().Background(back = R.drawable.background)

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.ligthning),
                contentDescription = "lightning",
                modifier = Modifier
                    .size((controller.screenHeight / 4).dp)
            )
            Templates().TemplateButton(controller, "PLAY AGAIN", 11, 3, fun() {
                val gameIntent = Intent(this@FinalActivity, MainActivity::class.java)
                startActivity(gameIntent)
                finish()
            })
            Templates().TemplateButton(controller, "MAIN MENU", 11, 2, fun() {
                val gameIntent = Intent(this@FinalActivity, MenuActivity::class.java)
                startActivity(gameIntent)
                finish()
            })
            Templates().TemplateTextField(controller, "SCORE: $score", 11, 1.5)
            Templates().TemplateTextField(controller, "RECORD: $record", 11, 1.5)
        }
    }
}