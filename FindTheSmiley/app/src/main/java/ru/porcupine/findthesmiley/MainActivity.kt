package ru.porcupine.findthesmiley

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
import androidx.lifecycle.lifecycleScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainAdapter = MainAdapter(this)
        mainAdapter.startTimer(lifecycleScope, this)

        setContent {
            Content(mainAdapter)
        }
    }

    @Composable
    fun Content(mainAdapter: MainAdapter) {
        Background()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Timer(mainAdapter)
                FindSmiley(mainAdapter)
                Count(mainAdapter)
            }
            SmileyGame(mainAdapter)
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
    fun Timer(mainAdapter: MainAdapter) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(25.dp)
                    .size(
                        height = (mainAdapter.screenHeight / 11).dp,
                        width = (mainAdapter.screenWidth / 4).dp
                    )
            ){
                Image(
                    painter = painterResource(id = R.drawable.big_btn),
                    contentDescription = "",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Text(
                    text = "Time: ${mainAdapter.timer} ",
                    textAlign = TextAlign.Center,
                    fontSize =16.sp,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.uf1))),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
    }

    @Composable
    fun Count(mainAdapter: MainAdapter) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(25.dp)
                .size(
                    height = (mainAdapter.screenHeight / 11).dp,
                    width = (mainAdapter.screenWidth / 4).dp
                )
        ){
            Image(
                painter = painterResource(id = R.drawable.big_btn),
                contentDescription = "",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = "Score: ${mainAdapter.count} ",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.uf1))),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun FindSmiley(mainAdapter: MainAdapter) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(mainAdapter.findSmiley.imgRes),
                "",
                Modifier
                    .padding(5.dp)
                    .size(mainAdapter.smileySize.dp)
            )
            Text(
                text = "FIND",
                style = TextStyle(fontFamily = FontFamily(Font(R.font.uf1))),
                fontSize = 18.sp
            )
        }
    }

    @Composable
    fun SmileyGame(mainAdapter: MainAdapter) {
        var index = 0
            Column(Modifier
                .padding(top=20.dp)) {
                for (i in 0..7) {
                    Row {
                        for (j in 0..4) {
                            SmileyCard(mainAdapter, index)
                            index++
                        }
                    }
                }
            }
    }

    @Composable
    fun SmileyCard(mainAdapter: MainAdapter, id:Int) {

        Image(
                painterResource(id = mainAdapter.smileyList.value[id].imgRes),
                "",
            Modifier
                .size(mainAdapter.smileySize.dp)
                .padding(5.dp)
                .clickable {
                    if (mainAdapter.smileyList.value[id].id == mainAdapter.findSmiley.id) {
                        mainAdapter.count++
                        mainAdapter.shuffleSmiley()
                    }
                }
        )
    }
}