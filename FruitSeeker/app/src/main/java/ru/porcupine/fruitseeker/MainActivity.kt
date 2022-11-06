package ru.porcupine.fruitseeker

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val record = SharedPreference(context = this@MainActivity).getValueInt("record")

        setContent {
            BackgroundImage()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp)
                    .padding(top = 200.dp),
                horizontalAlignment =Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.name),
                    contentDescription = "name",
                    modifier = Modifier
                        .fillMaxHeight(0.4f)
                        .fillMaxWidth()
                )
                Button(
                    onClick = {
                        startActivity(Intent(this@MainActivity, GameActivity::class.java))
                        finish()
                    }
                ) {
                    Text(
                        text = "Play",
                        modifier = Modifier.padding(15.dp)
                    )
                }

                if(record!=0) {
                    Text(
                        text ="Best Time: ${TimeSeparator().timeToString(record)}",
                        modifier = Modifier.padding(15.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700)
                    )
                }
            }

        }
    }

    @Composable
    fun BackgroundImage() {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}