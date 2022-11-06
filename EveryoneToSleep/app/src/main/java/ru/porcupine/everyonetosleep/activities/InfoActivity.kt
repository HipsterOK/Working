package ru.porcupine.everyonetosleep.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.everyonetosleep.Elements
import ru.porcupine.everyonetosleep.Logic
import ru.porcupine.everyonetosleep.R

class InfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameLogic = Logic(this)

        setContent {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Black)){
                Box(
                    Modifier
                        .fillMaxSize()
                        .alpha(0.4f)) {
                    Elements().Background(R.drawable.background)
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(25.dp)
                        .align(Alignment.TopEnd)
                ){
                    Elements().ButtonView(gameLogic = gameLogic, img = R.drawable.back, h = 12, w = 5) {
                        finish()
                    }
                }
                Text(
                    text = "In your wonderful city, in the middle of the night, problems with electricity began. Lights began to turn on everywhere, but people need to get enough sleep to get up for work in the morning. Your task is to help the residents of this city by clicking on the windows in which the light came on.\n" +
                            "All hope is on you!\n" +
                            "Good luck!",
                    textAlign = TextAlign.Center,
                    color = Color.Red,
                    fontSize = (gameLogic.screenHeight/35).sp,
                    style = TextStyle(fontFamily = FontFamily(Font(gameLogic.font))),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(20.dp)
//                    .background(Color.White)
                )
            }
        }
    }
}