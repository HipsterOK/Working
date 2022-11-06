package ru.porcupine.cockroachtrap

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainController = MainController(this)

        var record = SharedPreference(context = this).getValueInt("record")
        val delaySpawn = intent.getLongExtra("delay", 1000)
        val score = intent.getIntExtra("score", 0)

        if(score>record){
            record=score
            SharedPreference(this).save("record", record)
        }

        setContent {
            Content(mainController, record, score, delaySpawn)
        }
    }

    @Composable
    fun Content(mainController: MainController, record: Int, score: Int, delaySpawn: Long) {
        Background()
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Cockroach()
            Play(delaySpawn)
            Menu()
            Record(mainController, record)
            Score(mainController = mainController, score = score)
        }
    }

    @Composable
    fun Cockroach(){
        Image(
            painter = painterResource(id = R.drawable.sad_cockroach),
            contentDescription = "icon",
            alignment = Alignment.Center,
            modifier = Modifier
                .padding(20.dp)
        )
    }

    @Composable
    fun Play(delaySpawn: Long) {
        IconButton(onClick = {
                val mainIntent = Intent(this, MainActivity::class.java)
                mainIntent.putExtra("delay", delaySpawn)
                startActivity(mainIntent)
                finish()
        }) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.borders_button_red),
                    contentDescription = "",
                    alignment = Alignment.Center,
                )
                Text(
                    text = "Play Again",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.flow_b))),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }

    @Composable
    fun Menu() {
        IconButton(onClick = {
                val menuIntent = Intent(this, MenuActivity::class.java)
                startActivity(menuIntent)
                finish()

        }) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.borders_button_blue),
                    contentDescription = "",
                    alignment = Alignment.Center,
                )
                Text(
                    text = "Main Menu",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.flow_b))),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }

    @Composable
    fun Score(mainController: MainController, score: Int) {
        Box(Modifier.size(height = (mainController.screenHeight/16).dp, width = (mainController.screenWidth/2).dp)) {
            Image(
                painter = painterResource(id = R.drawable.borders_button_green),
                contentDescription = "",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = "Score $score",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.flow_b))),
                modifier = Modifier
                    .align(Alignment.Center)

            )
        }
    }

    @Composable
    fun Record(mainController: MainController, record: Int) {
        Box(Modifier.size(height = (mainController.screenHeight/16).dp, width = (mainController.screenWidth/2).dp)) {
            Image(
                painter = painterResource(id = R.drawable.borders_button_yellow),
                contentDescription = "",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = "Best result $record",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.flow_b))),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Background(){
        Image(
            painter = painterResource(id = R.drawable.menu_background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}