package ru.porcupine.woodcutterclicker.activities

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.woodcutterclicker.R
import ru.porcupine.woodcutterclicker.logic.MainLogic
import ru.porcupine.woodcutterclicker.tools.SharedPreference
import ru.porcupine.woodcutterclicker.ui.theme.Patterns

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
                Axe(mainLogic)
            }
            Instruction(mainLogic = mainLogic)
        }
    }

    @Composable
    fun Axe(mainLogic: MainLogic){
        Image(
            painter = painterResource(id = R.drawable.axe),
            contentDescription = "axe",
            modifier = Modifier
                .size(width = (mainLogic.axeWidth/1.5).dp, height = (mainLogic.axeHeight/1.5).dp)
        )
    }

    @Composable
    fun Name(mainLogic: MainLogic){
        Patterns().TemplateText(
            mainLogic = mainLogic,
            text = "Woodcutter\nClicker", h = 8f, w = 1f, font = 10f)
    }

    @Composable
    fun Play(context: Context, mainLogic:MainLogic) {
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
                text = "Get wood and upgrade!\n" +
                        "Click on the axe to mine the tree.\n" +
                        "You can hire workers, every second a worker will mine one tree.\n" +
                        "You can improve your axe - this is to increase income from one tap.\n" +
                        "When buying a new tree, you also increase the income from one tap.\n" +
                        "With the improvement of production, both your income and the income of employees increases.\n" +
                        "To win, you need to accumulate 1 million trees.",
                textAlign = TextAlign.Center,
                fontSize = (mainLogic.screenHeight/35).sp,
                color = colorResource(id = R.color.wood),
                style = TextStyle(fontFamily = FontFamily(Font(mainLogic.font))),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding((mainLogic.padding).dp)
//                    .background(Color.White)
            )
        }
    }
}