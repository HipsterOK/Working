package ru.porcupine.followme

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class StartActivity : ComponentActivity() {

    private var visible by mutableStateOf(0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameControl = GameController(this@StartActivity)

        val record = SharedPreference(context = this@StartActivity).getValueInt("record")

        setContent {
            Content(gameControl, record)
        }
    }

    @Composable
    fun Content(gameControl: GameController, record: Int) {
        Background()
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Name()
            Play(gameControl)
            Info(gameControl)
            Record(gameControl, record)
        }
        Instruction(gameControl)
    }

    @Composable
    fun Name(){
        Image(
            painter = painterResource(id = R.drawable.name),
            contentDescription = "icon",
            alignment = Alignment.Center,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .fillMaxWidth()
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
                    width = (gameControl.screenWidth / 3).dp
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
                        val mainIntent = Intent(this@StartActivity, MainActivity::class.java)
                        startActivity(mainIntent)
                        finish()
                    }
            )
            Text(
                text = "Play",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.monoton_regular))),
                color= Color.Red,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Info(gameControl: GameController) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(20.dp)
                .size(
                    height = (gameControl.screenHeight / 12).dp,
                    width = (gameControl.screenWidth / 3).dp
                )
        ){
            Image(
                painter = painterResource(id = R.drawable.border),
                contentDescription = "",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        visible = 1f
                    }
            )
            Text(
                text = "Help",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.monoton_regular))),
                color = Color.Red,
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
                    width = (gameControl.screenWidth / 2).dp
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
                fontSize = 18.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.monoton_regular))),
                color = Color.Red,
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

    @Composable
    fun Instruction(gameControl: GameController){
        Box(
            Modifier
                .fillMaxSize()
                .alpha(visible)
                .background(Color.Black)){
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(25.dp)
                    .size(
                        height = (gameControl.screenHeight / 15).dp,
                        width = (gameControl.screenWidth / 5).dp
                    )
                    .align(Alignment.TopEnd)
                    .alpha(visible),
            ){
                Image(
                    painter = painterResource(id = R.drawable.border),
                    contentDescription = "",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            visible = 0f
                        }
                        .alpha(visible)
                )
                Text(
                    text = "Back",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.monoton_regular))),
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .alpha(visible)
                )
            }
            Text(
                text = "Welcome to the game \"Follow Me!\"\n" +
                        "The main goal is to repeat the sequence after the bot.\n" +
                        "On the game screen there are 16 colored buttons, a score counter and the remaining health in the form of a battery charge.\n" +
                        "The further you go, the more difficult the sequence.\n" +
                        "Good luck!",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.monoton_regular))),
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.Center)
                    .alpha(visible)
                    .padding(20.dp)
//                    .background(Color.White)
            )
        }
    }

}