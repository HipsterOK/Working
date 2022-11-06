package ru.porcupine.trueorfalse.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.trueorfalse.Controller
import ru.porcupine.trueorfalse.R
import ru.porcupine.trueorfalse.SharedPreference

class ResultActivity : ComponentActivity() {

    private lateinit var score:MutableList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val controller = Controller(this, application)
        controller.startGame()
        score = intent.getIntegerArrayListExtra("score")!!.toMutableList()
        score.forEach {
            Log.i("res", it.toString())
        }

        setContent {
            Content(controller)
        }
    }

    @Composable
    fun Content(controller: Controller) {
        Background()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding((controller.screenWidth / 20).dp)
                .verticalScroll(rememberScrollState())
        ) {
            Score(controller)
            for(i in 0 until controller.playerCount){
                NamePlayersText(controller, i)
            }

            ReplayButton(controller)
            MenuButton(controller)
        }
    }

    @Composable
    private fun Score(controller: Controller) {
        Box(modifier = Modifier
            .padding(bottom = (controller.screenWidth / 20).dp)) {
            Image(
                painter = painterResource(id = R.drawable.red_border),
                contentDescription = "play border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (controller.screenHeight / 11).dp,
                        width = (controller.screenWidth).dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Score",
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                fontSize = (controller.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    private fun NamePlayersText(controller: Controller, i: Int) {
        Row(
            modifier= Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "${controller.playerList[i]}",
                style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                fontSize = (controller.screenWidth / 20).sp,
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = (controller.screenWidth / 20).dp)
            )

            Text(
                "${score[i]}",
                style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                fontSize = (controller.screenWidth / 20).sp,
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = (controller.screenWidth / 20).dp)
            )
        }
    }

    @Composable
    private fun ReplayButton(controller: Controller) {
        Box(modifier = Modifier.padding(bottom = (controller.screenWidth / 20).dp, top = (controller.screenWidth / 20).dp) ){
            Image(
                painter = painterResource(id = R.drawable.blue_border),
                contentDescription = "play border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (controller.screenHeight / 11).dp,
                        width = (controller.screenWidth / 4).dp
                    )
                    .clickable {
                        val playerIntent = Intent(this@ResultActivity, MainActivity::class.java)
                        startActivity(playerIntent)
                        finish()
                    },
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "TRY AGAIN",
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                fontSize = (controller.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    private fun MenuButton(controller: Controller) {
        Box(modifier = Modifier.padding(bottom = (controller.screenWidth / 20).dp, top = (controller.screenWidth / 20).dp) ) {
            Image(
                painter = painterResource(id = R.drawable.orange_border),
                contentDescription = "play border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (controller.screenHeight / 11).dp,
                        width = (controller.screenWidth / 5).dp
                    )
                    .clickable {
                        val playerIntent = Intent(this@ResultActivity, MenuActivity::class.java)
                        startActivity(playerIntent)
                        finish()
                    },
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "MENU",
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                fontSize = (controller.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    private fun Background() {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
