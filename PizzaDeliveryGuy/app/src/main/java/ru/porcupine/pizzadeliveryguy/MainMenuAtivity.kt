package ru.porcupine.pizzadeliveryguy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.pizzadeliveryguy.ui.theme.Shapes

class MainMenuAtivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val record = SharedPreference(context = this@MainMenuAtivity).getValueInt("record")
            setContent {
                val visTxt = remember { mutableStateOf(value = 0f) }
                BackgroundImage()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    IconButton(
                        onClick = {
                            val gamePage = Intent(this@MainMenuAtivity, MainActivity::class.java)
                            startActivity(gamePage)
                            finish()
                        },
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Icon(
                           painter = painterResource(id = R.drawable.play),
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(80.dp),
                            contentDescription = null,
                        )
                    }

                    IconButton(
                        onClick = {
                            if (visTxt.value == 0f) {
                                visTxt.value = 1f
                            } else visTxt.value = 0f
                        },
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.info),
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(80.dp),
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = "Best Time: $record",
                        fontWeight = FontWeight(800),
                        fontSize = 18.sp,
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.font))),
                        modifier = Modifier
                            .padding(10.dp)
                            .background(Color.LightGray, shape = Shapes.medium)
                    )
                    Text(
                        text = "You work as a pizza delivery guy.\n" +
                                "Your goal is to drive as far as possible, avoiding the flow of oncoming cars.\n" +
                                "Over time, you will gain more speed.\n" +
                                "To move to the left, tap and hold the left side of the screen.\n" +
                                "To move to the right, tap and hold the right side of the screen.\n" +
                                "Good luck!",
                        modifier = Modifier
                            .alpha(visTxt.value)
                            .padding(10.dp)
                            .background(Color.LightGray, shape = Shapes.medium),
                        fontWeight = FontWeight(700),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.font)))
                    )
                }
            }
        }

    }
    @Composable
    fun BackgroundImage() {
        Image(
            painter = painterResource(id = R.drawable.back_menu),
            contentDescription = "back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}