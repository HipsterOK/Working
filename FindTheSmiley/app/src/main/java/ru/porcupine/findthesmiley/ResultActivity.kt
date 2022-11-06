package ru.porcupine.findthesmiley

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

        val mainAdapter = MainAdapter(this)

        var record = SharedPreference(context = this).getValueInt("record")
        val score = intent.getIntExtra("score", 0)

        if(score>record){
            record=score
            SharedPreference(this).save("record", record)
        }

        setContent {
            Content(mainAdapter, record, score)
        }
    }

    @Composable
    fun Content(mainAdapter: MainAdapter, record: Int, score: Int) {
        Background()
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Smile()
            Play(mainAdapter)
            Menu(mainAdapter)
            Record(mainAdapter, record)
            Score(mainAdapter = mainAdapter, score = score)
        }
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
                        val mainIntent = Intent(this@ResultActivity, MainActivity::class.java)
                        startActivity(mainIntent)
                        finish()
                    }
            )
            Text(
                text = "Play Again",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.uf1))),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Menu(mainAdapter: MainAdapter) {
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
                        val startIntent = Intent(this@ResultActivity, StartActivity::class.java)
                        startActivity(startIntent)
                        finish()
                    }
            )
            Text(
                text = "Menu",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.uf1))),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Score(mainAdapter: MainAdapter, score: Int) {
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
                text = "Score: $score",
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
}