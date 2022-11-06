package ru.porcupine.findthesmiley

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

        val mainAdapter = MainAdapter(this@StartActivity)

        val record = SharedPreference(context = this@StartActivity).getValueInt("record")

        setContent {
            Content(mainAdapter, record)
        }
    }

    @Composable
    fun Content(mainAdapter: MainAdapter, record: Int) {
        Background()
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Smile()
            Play(mainAdapter)
            Info(mainAdapter)
            Record(mainAdapter, record)
        }
        Instruction(mainAdapter)
    }

    @Composable
    fun Smile(){
        Image(
            painter = painterResource(id = R.drawable.smile_9),
            contentDescription = "icon",
            alignment = Alignment.Center,
            modifier = Modifier
                .padding(20.dp)
        )
    }

    @Composable
    fun Play(mainAdapter: MainAdapter) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(20.dp)
                .size(
                    height = (mainAdapter.screenHeight / 12).dp,
                    width = (mainAdapter.screenWidth / 3).dp
                )) {
            Image(
                painter = painterResource(id = R.drawable.big_btn),
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
               style = TextStyle(fontFamily = FontFamily(Font(R.font.uf1))),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Info(mainAdapter: MainAdapter) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(20.dp)
                .size(
                    height = (mainAdapter.screenHeight / 12).dp,
                    width = (mainAdapter.screenWidth / 3).dp
                )
        ){
            Image(
                painter = painterResource(id = R.drawable.big_btn),
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
                text = "Info",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.uf1))),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Record(mainAdapter: MainAdapter, record: Int) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(25.dp)
                .size(
                    height = (mainAdapter.screenHeight / 11).dp,
                    width = (mainAdapter.screenWidth / 2).dp
                )){
            Image(
                painter = painterResource(id = R.drawable.big_btn),
                contentDescription = "",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = "Record: $record",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.uf1))),
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
    fun Instruction(mainAdapter: MainAdapter){
        Box(
            Modifier
                .fillMaxSize()
                .alpha(visible)
                .background(Color.White)){
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(25.dp)
                    .size(
                        height = (mainAdapter.screenHeight / 15).dp,
                        width = (mainAdapter.screenWidth / 6).dp
                    )
                    .align(Alignment.TopEnd)
                    .alpha(visible),
            ){
                Image(
                    painter = painterResource(id = R.drawable.big_btn),
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
                    text = "<-",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.uf1))),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .alpha(visible)
                )
            }
            Text(
                text = "How often have you come across the fact that during the dialogue you could not find the right smiley for a long time?\n" +
                        "In this game you will be able to upgrade your skill of finding the necessary emojis\n" +
                        "At the top of the screen are: a timer (you have 30 seconds), a smiley face that you need to find and a current account.\n" +
                        "In the middle of the screen you will find a lot of emoticons, among which you need to find the right one, for this you will get one point.\n" +
                        "Good luck!",
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.uf1))),
                modifier = Modifier
                    .align(Alignment.Center)
                    .alpha(visible)
                    .padding(20.dp)
//                    .background(Color.White)
            )
        }
    }

}