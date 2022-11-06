package ru.porcupine.meoworwoof

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var record = SharedPreference(context = this@ResultActivity).getValueInt("record")
        val score = intent.getIntExtra("score", 0)
        if(score>record){
            record = score
            SharedPreference(this).save("record", record)
        }
        val game = Game(this)

        setContent {
            GameElements().Background()
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Buttons().ButtonText(
                    game = game,
                    text = "Play Again",
                    h = 18f,
                    w = 3.5f,
                ){
                    val gameActivity = Intent(this@ResultActivity, MainActivity::class.java)
                    startActivity(gameActivity)
                    finish()
                }
                Buttons().ButtonText(
                    game = game,
                    text = "Back To Menu",
                    h = 18f,
                    w = 2.6f,
                ){
                    val gameActivity = Intent(this@ResultActivity, StartActivity::class.java)
                    startActivity(gameActivity)
                    finish()
                }
                Buttons().TextText(
                    game = game,
                    text = "Current Result: $score",
                    h = 18f,
                    w = 2.1f
                )

                Buttons().TextText(
                    game = game,
                    text = "Best Result: $record",
                    h = 18f,
                    w = 2.5f
                )
            }
        }
    }
}
