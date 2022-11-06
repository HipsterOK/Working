package ru.porcupine.buildapedestal.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.buildapedestal.other.Composablies
import ru.porcupine.buildapedestal.other.GameController
import ru.porcupine.buildapedestal.R
import ru.porcupine.buildapedestal.other.SharedPreference


class MenuActivity : ComponentActivity() {

    private var info by mutableStateOf(0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameController = GameController(this)

        setContent {
            MainContent(gameController)
        }
    }

    @Composable
    fun MainContent(gameController: GameController) {
        val record = SharedPreference(this).getValueInt("record")
        Composablies().Background(back = R.drawable.back_1)
        Box(Modifier.fillMaxHeight(0.2f)){
            Composablies().TemplateButton(gameController, "Privacy\npolicy", 10, 3, fun(){
                val url = getString(R.string.url)
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(this@MenuActivity, Uri.parse(url))
            })
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.character),
                contentDescription = "dama",
                modifier = Modifier
                    .size((gameController.screenHeight/2).dp),
            )
            Composablies().TemplateButton(gameController, "PLAY", 11, 3, fun(){
                val gameIntent = Intent(this@MenuActivity, MainActivity::class.java)
                startActivity(gameIntent)
                finish()
            })
            Composablies().TemplateButton(gameController, "INSTRUCTION", 11, 2, fun(){
                info = 1f
            })
            Composablies().TemplateTextField(gameController, "RECORD: $record", 11, 1.8)
        }
        InfoView(gameController)
    }
    @Composable
    private fun InfoView(gameController: GameController) {
        Box(
            Modifier
                .fillMaxSize()
                .alpha(info)
                .background(Color.White)){
            Box(Modifier.fillMaxSize().alpha(0.7f)) {
                Composablies().Background(back = R.drawable.back_1)
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(25.dp)
                    .size(
                        height = (gameController.screenHeight / 15).dp,
                        width = (gameController.screenWidth / 5).dp
                    )
                    .align(Alignment.TopEnd)
                    .alpha(info),
            ){
                Image(
                    painter = painterResource(id = R.drawable.text_border),
                    contentDescription = "",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            info = 0f
                        }
                        .alpha(info)
                )
                Text(
                    text = "Back",
                    textAlign = TextAlign.Center,
                    fontSize = (gameController.screenHeight/27).sp,
                    style = TextStyle(fontFamily = FontFamily(Font(gameController.font))),
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .alpha(info)
                )
            }
            Text(
                text = "Your main goal is to build a pedestal as high as possible.\n" +
                        "During the game, a piece of the pedestal will move from above, tap on the screen to drop this piece down, if you hit the center, the tower will grow and you will get a point, otherwise the piece will fall and you will lose health.\n" +
                        "After losing all health, the game will end.\n" +
                        "Good luck!",
                textAlign = TextAlign.Center,
                fontSize = (gameController.screenHeight/35).sp,
                style = TextStyle(fontFamily = FontFamily(Font(gameController.font))),
                modifier = Modifier
                    .align(Alignment.Center)
                    .alpha(info)
                    .padding(20.dp)
//                    .background(Color.White)
            )
        }
    }
}
