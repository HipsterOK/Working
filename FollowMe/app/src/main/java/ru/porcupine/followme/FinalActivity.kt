package ru.porcupine.followme

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class FinalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameControl = GameController(this)

        var record = SharedPreference(context = this).getValueInt("record")
        val score = intent.getIntExtra("score", 0)

        if(score>record){
            record=score
            SharedPreference(this).save("record", record)
        }

        setContent {
            Content(gameControl, record, score)
        }
    }

    @Composable
    fun Content(gameControl: GameController, record: Int, score: Int) {
        Background()
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Smile()
            Play(gameControl)
            Menu(gameControl)
            Record(gameControl, record)
            Score(gameControl = gameControl, score = score)
        }
    }

    @Composable
    fun Smile(){
        Image(
            painter = painterResource(id = R.drawable.name),
            contentDescription = "icon",
            alignment = Alignment.Center,
            modifier = Modifier
                .padding(20.dp)
        )
    }

    @Composable
    fun Play(gameControl: GameController) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(20.dp)
                .size(
                    height = (gameControl.screenHeight / 12).dp,
                    width = (gameControl.screenWidth / 2).dp
                )) {
            Image(
                painter = painterResource(id = R.drawable.border),
                contentDescription = "",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .clickable {
                        val mainIntent = Intent(this@FinalActivity, MainActivity::class.java)
                        startActivity(mainIntent)
                        finish()
                    }
            )
            Text(
                text = "Try Again!",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.Red,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.monoton_regular))),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Menu(gameControl: GameController) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(20.dp)
                .size(
                    height = (gameControl.screenHeight / 12).dp,
                    width = (gameControl.screenWidth / 2).dp
                )) {
            Image(
                painter = painterResource(id = R.drawable.border),
                contentDescription = "",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .clickable {
                        val startIntent = Intent(this@FinalActivity, StartActivity::class.java)
                        startActivity(startIntent)
                        finish()
                    }
            )
            Text(
                text = "Main Menu",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.Red,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.monoton_regular))),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Score(gameControl: GameController, score: Int) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(25.dp)
                .size(
                    height = (gameControl.screenHeight / 11).dp,
                    width = (gameControl.screenWidth).dp
                )){
            Image(
                painter = painterResource(id = R.drawable.border),
                contentDescription = "",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = "Result: $score",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.Red,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.monoton_regular))),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Record(gameControl: GameController, record: Int) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(25.dp)
                .size(
                    height = (gameControl.screenHeight / 11).dp,
                    width = (gameControl.screenWidth).dp
                )){
            Image(
                painter = painterResource(id = R.drawable.border),
                contentDescription = "",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = "Best Result: $record",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.Red,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.monoton_regular))),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Background() {
        Image(painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)
    }
}