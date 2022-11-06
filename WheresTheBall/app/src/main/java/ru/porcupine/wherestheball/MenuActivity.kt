package ru.porcupine.wherestheball

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

class MenuActivity : ComponentActivity() {
    private var visible by mutableStateOf(0f)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val controller = Controller(this)

        setContent {
            Content(controller)
        }
    }

    @Composable
    fun Content(controller: Controller) {
        Background()
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CurrentCount(controller)
            BestCount(controller)
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
                Play(controller)
                Info(controller)
        }
        InfoText(controller)
    }

    @Composable
    fun CurrentCount(controller: Controller) {
        Box(Modifier.padding(start = (controller.screenWidth/20).dp, top = (controller.screenWidth/20).dp)){
            Image(
                painter = painterResource(id = R.drawable.blue_border),
                contentDescription = "border",
                modifier = Modifier
                    .size(
                        height = (controller.screenHeight / 7).dp,
                        width = (controller.screenWidth / 3).dp
                    )
                    .align(Alignment.Center),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Current \n" +
                        "balance: \n" +
                        "${controller.balance}",
                fontSize = (controller.screenHeight/30).sp,
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.small_print))),
                modifier = Modifier
                    .align(Alignment.Center),
            )
        }
    }

    @Composable
    fun BestCount(controller: Controller) {
        Box(Modifier.padding(end = (controller.screenWidth/20).dp, top = (controller.screenWidth/20).dp)){
            Image(
                painter = painterResource(id = R.drawable.blue_border),
                contentDescription = "border",
                modifier = Modifier
                    .size(
                        height = (controller.screenHeight / 7).dp,
                        width = (controller.screenWidth / 3).dp
                    )
                    .align(Alignment.Center),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Best \n" +
                        "balance: \n" +
                        "${controller.bestBalance}",
                fontSize = (controller.screenHeight/30).sp,
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.small_print))),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Play(controller: Controller) {
        Box(Modifier.padding((controller.screenHeight/30).dp)){
            Image(
                painter = painterResource(id = R.drawable.red_border),
                contentDescription = "border",
                modifier = Modifier
                    .size(
                        height = (controller.screenHeight / 6).dp,
                        width = (controller.screenWidth / 2).dp
                    )
                    .align(Alignment.Center)
                    .clickable {
                        startActivity(Intent(this@MenuActivity, MainActivity::class.java))
                        finish()
                    },
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "PLAY",
                fontSize = (controller.screenHeight/12).sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.small_print))),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Info(controller: Controller) {
        Box(Modifier.padding((controller.screenHeight/30).dp)){
            Image(
                painter = painterResource(id = R.drawable.yellow_border),
                contentDescription = "border",
                modifier = Modifier
                    .size(
                        height = (controller.screenHeight / 6).dp,
                        width = (controller.screenWidth / 2).dp
                    )
                    .align(Alignment.Center)
                    .clickable {
                        visible = 1f
                    },
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "INFO",
                fontSize = (controller.screenHeight/12).sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.small_print))),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
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

    @Composable
    fun InfoText(controller: Controller) {
            Box(
                Modifier
                    .fillMaxSize()
                    .alpha(visible)
                    .background(Color.Black)){
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(25.dp)
                        .size(
                            height = (controller.screenHeight / 15).dp,
                            width = (controller.screenWidth / 5).dp
                        )
                        .align(Alignment.TopEnd)
                        .alpha(visible),
                ){
                    Image(
                        painter = painterResource(id = R.drawable.red_border),
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
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.small_print))),
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .alpha(visible)
                    )
                }
                Text(
                    text = "Welcome!\n" +
                            "The goal of the game is to remember which cup the ball is under and earn as many points as possible.\n" +
                            "Initially, you have 500 points.\n" +
                            "You can choose how many points you are willing to spend.\n" +
                            "If you win, your bet points are doubled, if you lose, you lose the bet points.\n" +
                            "Good luck!",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.small_print))),
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .alpha(visible)
                        .padding(20.dp)
//                    .background(Color.White)
                )
            }
    }
}