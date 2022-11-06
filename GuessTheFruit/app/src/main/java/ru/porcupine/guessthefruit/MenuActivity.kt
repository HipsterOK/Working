package ru.porcupine.guessthefruit

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.guessthefruit.ui.theme.GuessTheFruitTheme

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Content()
        }
    }

    @Composable
    fun Content(){
        Background()
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
            Start()
            Info()
        }
    }

    @Composable
    fun Start() {
        Box(
            modifier = Modifier
                .padding(25.dp)
                .fillMaxWidth(),
        ){
          Image(
              painter = painterResource(id = R.drawable.border_1),
              contentDescription = "border play",
              modifier = Modifier
                  .align(Alignment.Center)
                  .clickable {
                      val gameIntent = Intent(this@MenuActivity, MainActivity::class.java)
                      startActivity(gameIntent)
                      finish()
                  }
          )
            Text(
                text = "PLAY",
                style = TextStyle(fontFamily = FontFamily(Font(R.font.burst_my_bubble))),
                textAlign = TextAlign.Center,
                fontSize = (Controller(this@MenuActivity).screenWidth/7).sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Info() {
        Text(
            text = "Your goal is to guess the word on the topic \"Fruit\".\n" +
                    "A word is hidden in the middle of the screen.\n" +
                    "The alphabet is shown at the bottom of the screen. When you click on a letter, if it is in the word, it will be displayed, \n" +
                    "otherwise you will lose one heart.\n" +
                    "Good luck!",
            style = TextStyle(fontFamily = FontFamily(Font(R.font.burst_my_bubble))),
            textAlign = TextAlign.Center,
            fontSize = (Controller(this@MenuActivity).screenWidth/15).sp,
            modifier = Modifier.padding((Controller(this).screenWidth/200).dp)
        )
    }

    @Composable
    fun Background() {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}