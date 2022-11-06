package ru.porcupine.colorcommotion.screens

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.colorcommotion.R
import ru.porcupine.colorcommotion.controllers.GameController

class MenuScreen(private val context: Context) {
    @Composable
    fun MenuContent(gameController: GameController) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(gameController.screenController.menuPos.dp, 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().align(Alignment.Start)
                ){
                    PrivacyPolicy(gameController)
                }
                Game(gameController)
                Patterns(context).TextPattern(text = "Click on the colored buttons in the order written on top", gameController = gameController)
                Patterns(context).ButtonPattern(gameController = gameController,
                    text = "PLAY",
                ) {
                    gameController.screenController.goGame(gameController)
                }
                Patterns(context).TextPattern(text = "Best Result: ${gameController.variables.record}", gameController = gameController)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Patterns(context).Person(image = R.drawable.hero1, gameController)
                    Patterns(context).Person(image = R.drawable.hero2, gameController)
                }
            }
        }
    }

    @Composable
    fun Game(gameController: GameController){
        Text(
            text = "COLOR COMMOTION",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.textSize*2).sp,
            color = Color.White,
            modifier = Modifier
                .padding((gameController.constants.padding).dp)
        )
    }

    @Composable
    fun PrivacyPolicy(gameController: GameController){
        Card(
            Modifier
                .padding(gameController.constants.padding.dp)
                .clickable {
                    val url = context.getString(R.string.url)
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(context, Uri.parse(url))
                },
            elevation = (gameController.constants.screenWidth/30).dp,
            backgroundColor = Color.Blue
        ) {
            Text(
                text = "Privacy\nPolicy",
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
                fontSize = (gameController.constants.textSize/2).sp,
                color = Color.White,
                modifier = Modifier
                    .padding((gameController.constants.padding).dp)
            )
        }
    }
}