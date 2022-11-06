package ru.porcupine.oopsthisisthewrongwc

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val record = SharedPreference(this).getValueInt("record")
        val game = Game(this)

        setContent {
            GameElements().Background()
            var info by remember {
                mutableStateOf(0f)
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Buttons().ButtonText(
                    game = game,
                    text = "Play",
                    h = 18f,
                    w = 4f,
                ){
                    val gameActivity = Intent(this@StartActivity, MainActivity::class.java)
                    startActivity(gameActivity)
                    finish()
                }
                Buttons().ButtonText(
                    game = game,
                    text = "Instruction",
                    h = 18f,
                    w = 3f,
                ){
                    info = if(info==0f){
                        1f
                    } else 0f
                }
                Buttons().TextText(
                    game = game,
                    text = "Best Result: $record",
                    h = 18f,
                    w = 3f
                )

                Text(
                    text = "Welcome!\n" +
                            "Your goal is to score as many points as possible in 30 seconds by clicking the right or left side of the screen to send people to the booth they need. If you do the right thing, you get one point, but if you make a mistake, you lose 3 points. Be careful, the booths may change places.",
                    textAlign = TextAlign.Center,
                    fontSize = (game.screenHeight/35).sp,
                    style = TextStyle(fontFamily = FontFamily(Font(game.font))),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .alpha(info)
                        .padding(20.dp)
//                    .background(Color.White)
                )
            }
        }
    }
}
