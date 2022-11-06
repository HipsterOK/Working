package ru.porcupine.onecircletwocircle.graphics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.onecircletwocircle.R
import ru.porcupine.onecircletwocircle.controller.GameController
import ru.porcupine.onecircletwocircle.controller.InfoController

class InfoUI {
    @Composable
    fun InfoView(gameController: GameController, infoController: InfoController) {
        Box(
            Modifier
                .fillMaxSize()
                .alpha(infoController.alpha)){
            Template().Background()
            Box(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxSize().padding(gameController.padding.dp)) {
                Template().Button(
                    gameController = gameController,
                    text = "Home",
                    h = 14f,
                    w = 3f,
                    font = 20f
                ) {
                    infoController.alpha = 0f
                }
            }

            Text(
                text = "Welcome!\n" +
                        "Tap the screen to push and slide the green balls.\n" +
                        "Balls will fall from above, your goal is to catch green balls with green ones, and avoid black ones.\n" +
                        "With each ball caught, the speed of the game will increase.\n" +
                        "Good luck!",
                textAlign = TextAlign.Center,
                fontSize = (gameController.screenHeight/35).sp,
                color = colorResource(id = R.color.green),
                style = TextStyle(fontFamily = FontFamily(Font(gameController.font))),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding((gameController.padding*2).dp)
//                    .background(Color.White)
            )
        }
    }
}