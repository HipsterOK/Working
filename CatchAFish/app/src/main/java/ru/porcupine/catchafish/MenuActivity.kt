package ru.porcupine.catchafish

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val record = SharedPreference(context = this@MenuActivity).getValueInt("record")
            setContent {
                val visTxt = remember { mutableStateOf(value = 0f) }
                BackgroundImage()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            val gamePage = Intent(this@MenuActivity, MainActivity::class.java)
                            startActivity(gamePage)
                            finish()
                        },
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "PLAY",
                            fontWeight = FontWeight(700),
                            fontSize = 16.sp
                        )
                    }
                    Text(
                        text = "Best Result: $record",
                        fontWeight = FontWeight(700),
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(10.dp)
                    )

                    Button(
                        onClick = {
                            if (visTxt.value == 0f) {
                                visTxt.value = 1f
                            } else visTxt.value = 0f
                        },
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Text(text = "Info", fontSize = 20.sp)
                    }
                    Text(
                        text = "Tap on the right side of the screen to move the cat to the right. \n" +
                                "Tap on the left side of the screen to move the cat to the left. \n" +
                                "Catch fish to earn points. \n" +
                                "Be careful, when catching bones from fish, you will lose health. \n" +
                                "In total you have 3 HP. \n" +
                                "Good luck!",
                        modifier = Modifier
                            .alpha(visTxt.value)
                            .padding(10.dp),
                        fontWeight = FontWeight(600),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
    @Composable
    fun BackgroundImage() {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
