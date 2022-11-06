package ru.porcupine.rabbithole.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.rabbithole.R
import ru.porcupine.rabbithole.controllers.GameController

class Info {
    @Composable
    fun InfoContent(gameController: GameController) {
        val pos by animateDpAsState(targetValue = gameController.screenController.infoX.dp)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(pos, 0.dp)
        ) {
            Background()
            Column {
                Back(gameController)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(gameController)
                }
            }
        }
    }

    @Composable
    fun Text(gameController: GameController) {
        Text(
            text = "Welcome!\n" +
                    "Your goal is to guide the rabbit through the carrots, but you do not know in which bed the carrots will be.\n" +
                    "There are 2 carrots in each row, if you guessed right, then your points increase by 1.5 times, and also restores one health. And if you have not guessed correctly, then you lose one life.\n" +
                    "Good luck!",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.screenWidth / 15).sp,
            color = colorResource(id = R.color.black),
            modifier = Modifier
                .padding(gameController.constants.padding.dp)
                .clickable {
                    gameController.screenController.goMenu(gameController)
                }
        )
    }

    @Composable
    fun Back(gameController: GameController) {
        Text(
            text = "Back",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.screenWidth / 20).sp,
            color = colorResource(id = R.color.black),
            modifier = Modifier
                .padding(gameController.constants.padding.dp)
                .clickable {
                    gameController.screenController.goMenu(gameController)
                }
        )
    }

    @Composable
    fun Background() {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "back",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}