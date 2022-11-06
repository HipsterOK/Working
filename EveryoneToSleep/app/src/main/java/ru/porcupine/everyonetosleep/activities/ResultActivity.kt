package ru.porcupine.everyonetosleep.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.porcupine.everyonetosleep.Elements
import ru.porcupine.everyonetosleep.Logic
import ru.porcupine.everyonetosleep.R
import ru.porcupine.everyonetosleep.SharedPreference

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameLogic = Logic(this)

        setContent {
            val score = intent.getIntExtra("score", 0)
            var record = SharedPreference(this).getValueInt("record")
            if(score > record) {
                record = score
                SharedPreference(this).save("record", score)
            }
                Elements().Background(R.drawable.background)
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Elements().TextView(
                        gameLogic = gameLogic,
                        text = "Score: $score",
                        h = 15,
                        w = 3.2
                    )

                    Elements().TextView(
                        gameLogic = gameLogic,
                        text = "Best: $record",
                        h = 15,
                        w = 3.8
                    )
Row(
    Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceEvenly
) {
    Elements().ButtonView(gameLogic, R.drawable.play, 12, 5, fun() {
        val gameIntent = Intent(this@ResultActivity, MainActivity::class.java)
        startActivity(gameIntent)
        finish()
    })

    Elements().ButtonView(gameLogic, R.drawable.menu, 12, 5, fun() {
        val gameIntent = Intent(this@ResultActivity, MenuActivity::class.java)
        startActivity(gameIntent)
        finish()
    })
}
            }
        }
    }
}