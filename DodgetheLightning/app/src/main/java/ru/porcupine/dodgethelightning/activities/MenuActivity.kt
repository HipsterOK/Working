package ru.porcupine.dodgethelightning.activities

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
import ru.porcupine.dodgethelightning.Controller
import ru.porcupine.dodgethelightning.R
import ru.porcupine.dodgethelightning.SharedPreference
import ru.porcupine.dodgethelightning.Templates


class MenuActivity : ComponentActivity() {

    private var info by mutableStateOf(0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val controller = Controller(this)

        setContent {
            MainContent(controller)
        }
    }

    @Composable
    fun MainContent(controller: Controller) {
        val record = SharedPreference(this).getValueInt("recordDodgeTheLightning")
        Templates().Background(back = R.drawable.background)
        Box(Modifier.fillMaxHeight(0.2f)){
            Templates().TemplateButton(controller, "Privacy\npolicy", 10, 3, fun(){
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
                painter = painterResource(id = R.drawable.ligthning),
                contentDescription = "lightning",
                modifier = Modifier
                    .size((controller.screenHeight/4).dp)
            )
            Templates().TemplateButton(controller, "PLAY", 11, 3, fun(){
                val gameIntent = Intent(this@MenuActivity, MainActivity::class.java)
                startActivity(gameIntent)
                finish()
            })
            Templates().TemplateButton(controller, "INSTRUCTION", 11, 2, fun(){
                info = 1f
            })
            Templates().TemplateTextField(controller, "RECORD: $record", 11, 1.5)
        }
        InfoText(controller = controller)
    }
    @Composable
    private fun InfoText(controller: Controller) {
        Box(
            Modifier
                .fillMaxSize()
                .alpha(info)
                .background(Color.Black)){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(25.dp)
                    .size(
                        height = (controller.screenHeight / 15).dp,
                        width = (controller.screenWidth / 5).dp
                    )
                    .align(Alignment.TopEnd)
                    .alpha(info),
            ){
                Image(
                    painter = painterResource(id = R.drawable.red_border),
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
                    fontSize = (controller.screenHeight/27).sp,
                    style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .alpha(info)
                )
            }
            Text(
                text = "Welcome!\n" +
                        "You play as a dog who went out for a walk, but got caught in a heavy downpour, the only way out is to hide under a tree, but you need to be careful, because a thunderstorm has started outside, during a thunderbolt you need to get out from under the tree.\n" +
                        "In order to control the dog, just tap on the screen.\n" +
                        "When you are under a tree, you accumulate points, but when you get out from under it, you lose points.\n" +
                        "Lightning will appear faster each time.\n" +
                        "Good luck!",
                textAlign = TextAlign.Center,
                fontSize = (controller.screenHeight/35).sp,
                style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .alpha(info)
                    .padding(20.dp)
//                    .background(Color.White)
            )
        }
    }
}
