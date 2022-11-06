package ru.porcupine.petthedog.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.petthedog.logic.MainLogic
import ru.porcupine.petthedog.tools.SharedPreference
import ru.porcupine.petthedog.ui.theme.Patterns
import ru.porcupine.petthedog.R

class MenuActivity : ComponentActivity() {

    private var infoAlpha by mutableStateOf(0f)
    private var infoX by mutableStateOf(0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainLogic = MainLogic(this)
        infoX = mainLogic.screenWidth

        setContent {
            Patterns().Background()
            Reset(mainLogic = mainLogic)
            Column(Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom) {
                Name(mainLogic)
                Play(context = this@MenuActivity, mainLogic = mainLogic)
                Info(mainLogic = mainLogic)
                Dog(mainLogic)
            }
            Instruction(mainLogic = mainLogic)
        }
    }

    @Composable
    fun Dog(mainLogic: MainLogic){
        Image(
            painter = painterResource(id = R.drawable.dog),
            contentDescription = "dog",
            modifier = Modifier
                .size(width = (mainLogic.dogWidth/1.5).dp, height = (mainLogic.dogHeight/1.5).dp)
        )
    }

    @Composable
    fun Name(mainLogic: MainLogic){
        Patterns().TemplateText(
            mainLogic = mainLogic,
            text = "Woodcutter\nClicker", h = 8f, w = 1f, font = 10f)
    }

    @Composable
    fun Play(context: Context, mainLogic: MainLogic) {
        Patterns().Button(
            mainLogic = mainLogic,
            text = "Play",
            h = 14f,
            w = 3f,
            font = 20f
        ) {
            val mainActivity = Intent(context, MainActivity::class.java)
            startActivity(mainActivity)
            finish()
        }
    }

    @Composable
    fun Info(mainLogic: MainLogic) {
        Patterns().Button(
            mainLogic = mainLogic,
            text = "Info",
            h = 14f,
            w = 3f,
            font = 20f
        ) {
            infoAlpha=1f
            infoX = 0f
        }
    }

    @Composable
    fun Reset(mainLogic: MainLogic) {
        Patterns().Button(
            mainLogic = mainLogic,
            text = "Reset\nProgress",
            h = 12f,
            w = 3.3f,
            font = 20f
        ) {
            SharedPreference(this).save("balance", 0)
            SharedPreference(this).save("oneTap", 1)
            SharedPreference(this).save("oneSecond", 0)
            SharedPreference(this).save("price0", 10)
            SharedPreference(this).save("price1", 10)
            SharedPreference(this).save("price2", 100)
            SharedPreference(this).save("price3", 200)
            SharedPreference(this).save("price4", 1000000)
        }
    }

    @Composable
    fun Instruction(mainLogic: MainLogic) {
        Box(
            Modifier
                .fillMaxSize()
                .alpha(infoAlpha)
                .offset(x = infoX.dp, y = 0.dp)
        ){
            Patterns().Background()
            Box(contentAlignment = Alignment.TopStart, modifier = Modifier
                .fillMaxSize()
                .padding(mainLogic.padding.dp)) {
                Patterns().Button(
                    mainLogic = mainLogic,
                    text = "Home",
                    h = 14f,
                    w = 3f,
                    font = 20f
                ) {
                    infoAlpha = 0f
                    infoX = mainLogic.screenWidth
                }
            }

            Text(
                text = "Stroke the dog and improve!\n" +
                        "Clicking on the dog to get hearts.\n" +
                        "You can buy a new toy, every second it will bring one heart.\n" +
                        "You can improve your comb - it's to increase income with one tap.\n" +
                        "By buying a new shampoo, you also increase the revenue from one click.\n" +
                        "With the purchase of new food, both your income and the income of employees increases.\n" +
                        "To win, you need to accumulate 1 million hearts.",
                textAlign = TextAlign.Center,
                fontSize = (mainLogic.screenHeight/35).sp,
                color = Color.Black,
                style = TextStyle(fontFamily = FontFamily(Font(mainLogic.font))),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding((mainLogic.padding).dp)
//                    .background(Color.White)
            )
        }
    }
}