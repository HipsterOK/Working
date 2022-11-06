package ru.porcupine.whostolemycheese.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.whostolemycheese.R
import ru.porcupine.whostolemycheese.utils.Controller


class InfoScreen {
    @Composable
    fun InfoContent(controller: Controller) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(controller.screensController.infoPos.dp, 0.dp)
        ) {
            MenuScreen().Back()
            Column(
                modifier = Modifier
                .align(Alignment.Center)
            ) {
                Back(controller)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(controller)
                }
            }
        }
    }

    @Composable
    fun Text(controller: Controller) {
        Text(
            text = "Welcome!\n" +
                    "Tap on the screen to eat cheese and earn points by playing as a mouse until the cat sees.\n" +
                    "The cat gets out in several stages, when she looks out slightly, she does not see you, but she is preparing to go to the middle, where she will already be able to notice you.\n" +
                    "Good luck!",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(controller.constants.font))),
            fontSize = (controller.constants.screenWidth / 35).sp,
            color = Color.Black,
            modifier = Modifier
                .padding(controller.constants.padding.dp)
        )
    }

    @Composable
    fun Back(controller: Controller) {
        Card(
            elevation = 10.dp,
            backgroundColor = colorResource(id = R.color.purple_200),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .padding(controller.constants.padding.dp)
                .clickable {
                    controller.screensController.menu()
                }
        ) {
            Text(
                text = "Back",
                modifier = Modifier.padding(controller.constants.padding.dp),
                fontSize = (controller.constants.screenWidth / 30).sp,
                color = Color.Black,
                style = TextStyle(fontFamily = FontFamily(Font(controller.constants.font))),
            )
        }
    }
}