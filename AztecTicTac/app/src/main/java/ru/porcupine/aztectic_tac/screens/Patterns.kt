package ru.porcupine.aztectic_tac.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.aztectic_tac.controllers.GameController
import ru.porcupine.aztectic_tac.R

class Patterns {
    @Composable
    fun Button(gameController: GameController, text: String, h: Float, w: Float, font:Float, func: () -> Unit){
        Box(
            Modifier.padding(gameController.constants.padding.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.button_back),
                contentDescription = "player border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (gameController.constants.screenHeight / h).dp,
                        width = (gameController.constants.screenWidth / w).dp
                    )
                    .clickable {
                        func.invoke()
                    },
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
                fontSize = (gameController.constants.screenWidth / font).sp,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding((gameController.constants.padding / 2).dp)
            )
        }
    }

    @Composable
    fun Text(gameController: GameController, text: String, h: Float, w: Float, font:Float){
        Box(
            Modifier.padding(gameController.constants.padding.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.text_back),
                contentDescription = "player border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (gameController.constants.screenHeight / h).dp,
                        width = (gameController.constants.screenWidth / w).dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
                fontSize = (gameController.constants.screenWidth / font).sp,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding((gameController.constants.padding).dp)
            )
        }
    }

    @Composable
    fun Background() {
        Image(
            painter = painterResource(id = R.drawable.bacck),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}