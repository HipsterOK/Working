package ru.porcupine.cockroachtrap

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val delaySpawn = intent.getLongExtra("delay", 0).toLong()

        val mainController = MainController(this)
        Log.i("Delay", "Main $delaySpawn")

        mainController.spawnCockroach(delaySpawn)
        mainController.startTimer(this, delaySpawn)
        setContent {
            Content(mainController)
        }
    }

    @Composable
    fun Content(mainController: MainController) {
        Background()
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
            Timer(mainController)
            Score(mainController)
        }
        Cockroach(mainController)

    }

    @Composable
    fun Timer(mainController: MainController) {
        Box(Modifier.size(height = (mainController.screenHeight/17).dp, width = (mainController.screenWidth/3).dp)) {
            Image(
                painter = painterResource(id = R.drawable.borders_button_green),
                contentDescription = "",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
//                    .size(height = (mainController.screenHeight/18).dp, width = (mainController.screenWidth/6).dp)
            )
            Text(
                text = "Time ${mainController.timer}",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.flow_b))),
                modifier = Modifier
                    .align(Alignment.Center)
//                    .size(200.dp)
            )
        }
    }

    @Composable
    fun Score(mainController: MainController) {
        Box(Modifier.size(height = (mainController.screenHeight/17).dp, width = (mainController.screenWidth/3).dp)) {
            Image(
                painter = painterResource(id = R.drawable.borders_button_green),
                contentDescription = "",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
//                    .size(height = (mainController.screenHeight/18).dp, width = (mainController.screenWidth/6).dp)
            )
            Text(
                text = "Score ${mainController.score}",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.flow_b))),
                modifier = Modifier
                    .align(Alignment.Center)
//                    .size(200.dp)
            )
        }
    }

    @Composable
    fun Cockroach(mainController: MainController) {
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.cockroach),
                contentDescription = "icon",
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(mainController.cockroachSize.dp)
                    .absoluteOffset(
                        x = mainController.cockroachX.dp,
                        y = mainController.cockroachY.dp
                    )
                    .clickable(indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {

                        if (mainController.cockroachEnable) {
                            mainController.score++
                            mainController.cockroachEnable = false
                            Log.i("Hit", "Hit")
                        }
                    })
            )
        }
    }

    @Composable
    fun Background(){
        Image(
            painter = painterResource(id = R.drawable.game_background),
            contentDescription = "background",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
    }
}