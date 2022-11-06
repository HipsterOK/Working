package ru.porcupine.aztectic_tac.screens

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import ru.porcupine.aztectic_tac.R
import ru.porcupine.aztectic_tac.controllers.GameController
import ru.porcupine.aztectic_tac.controllers.InfoController
import ru.porcupine.aztectic_tac.utils.Intents

class MenuScreen(context: Context) {
    private val gameController = GameController(context)

//    @Composable
//    fun PlayerPlane(gameController: GameController){
//        Image(
//            painter = painterResource(id = R.drawable.airplane),
//            contentDescription = "plane",
//            modifier = Modifier
//                .size(
//                    width = (gameController.const.widthPlane*3).dp,
//                    height = (gameController.const.heightPlane*3).dp
//                )
//        )
//    }

    @Composable
    fun ButtonPlay(context: Context) {
        Patterns().Button(
            gameController = gameController,
            text = "Play",
            h = 14f,
            w = 3f,
            font = 20f
        ) {
            Intents().game(context)
        }
    }

    @Composable
    fun ButtonInstruction(infoController: InfoController) {
        Patterns().Button(
            gameController = gameController,
            text = "Info",
            h = 14f,
            w = 3f,
            font = 20f
        ) {
            infoController.alpha=1f
            infoController.posY = 0f
        }
    }

    @Composable
    fun ButtonPolicy(context: Context) {
        Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.fillMaxSize().padding(gameController.constants.padding.dp)) {
            Patterns().Button(
                gameController = gameController,
                text = "Privacy \n policy",
                h = 10f,
                w = 3.5f,
                font = 18f
            ) {
                val url = context.getString(R.string.site)
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(context, Uri.parse(url))
            }
        }
    }

    @Composable
    fun TextRecord(record: Int) {
        Patterns().Text(
            gameController = gameController,
            text = "Record: $record",
            h = 10f,
            w = 2f,
            font = 20f
        )
    }

    @Composable
    fun Info(gameController: GameController, infoController: InfoController) {
        Box(
            Modifier
                .fillMaxSize()
                .alpha(infoController.alpha)
                .offset(0.dp, infoController.posY.dp)){
            Patterns().Background()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.8f)
                    .padding(gameController.constants.padding.dp)
                    .background(Color.White, CircleShape)
            ){}
            Box(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxSize().padding(gameController.constants.padding.dp)) {
                Patterns().Button(
                    gameController = gameController,
                    text = "Home",
                    h = 14f,
                    w = 3f,
                    font = 20f
                ) {
                    infoController.alpha = 0f
                    infoController.posY = gameController.constants.screenHeight
                }
            }
            Text(
                text = "Welcome!\n" +
                        "Your goal is to beat your opponent in tic-tac-toe.\n" +
                        "If you win, you get one point.\n" +
                        "In case of defeat, the game ends.\n" +
                        "Earn as many points as possible.\n" +
                        "Good luck!",
                textAlign = TextAlign.Center,
                fontSize = (gameController.constants.screenHeight/35).sp,
                color = Color.Black,
                style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding((gameController.constants.padding*2).dp)
//                    .background(Color.White)
            )
        }
    }

    @Composable
    fun Character(image:Int, gameController: GameController){
        Image(
            painter = painterResource(id = image),
            contentDescription = "character",
            modifier = Modifier
                .size(gameController.constants.sizeCharacter[0].dp)
        )
    }
}
