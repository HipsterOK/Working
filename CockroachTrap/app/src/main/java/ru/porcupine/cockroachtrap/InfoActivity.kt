package ru.porcupine.cockroachtrap

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val delay = intent.getLongExtra("delay", 1000)
        val originalText = "Welcome to the \"Cockroach Trap\"!\n" +
                "There are cockroaches on your kitchen table.\n" +
                "Your goal is to catch as many cockroaches as possible in 20 seconds by clicking on them.\n" +
                "Good luck!"

        SharedPreference(this).save("first",1)

        setContent {
            val coroutineScope = rememberCoroutineScope()
            var text by remember {
                mutableStateOf("")
            }

            var alphaButton by remember {
                mutableStateOf(0f)
            }

            LaunchedEffect(key1 = ""){
                coroutineScope.launch(Dispatchers.IO) {
                    var i=0
                    while(i<originalText.length) {
                        text += originalText[i]
                        i++
                        delay(15)
                    }
                    delay(1000)
                    alphaButton=1f
                }
            }

            Column(modifier = Modifier.fillMaxSize(), Arrangement.Center) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = text,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.flow_b))),
                        modifier = Modifier.padding(15.dp)
                    )
                }

                IconButton(
                    onClick = {
                        val mainIntent = Intent(this@InfoActivity, MainActivity::class.java)
                        mainIntent.putExtra("delay", delay)
                        startActivity(mainIntent)
                        finish()
                    },
                    Modifier.alpha(alphaButton)
                ) {
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.borders_button_blue),
                            contentDescription = "",
                            alignment = Alignment.Center,
                        )
                        Text(
                            text = "Lets Go!",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            style = TextStyle(fontFamily = FontFamily(Font(R.font.flow_b))),
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}
