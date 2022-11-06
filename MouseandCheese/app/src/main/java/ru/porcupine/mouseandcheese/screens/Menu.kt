package ru.porcupine.mouseandcheese.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import ru.porcupine.mouseandcheese.controllers.GameController
import ru.porcupine.mouseandcheese.R

class Menu {
    @Composable
    fun MenuContent(gameController: GameController) {
        val pos by animateDpAsState(targetValue = gameController.screenController.menuX.dp)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(pos, 0.dp)
        ) {
            Background()
            Info(gameController)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(gameController.constants.padding.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Player(gameController)
                Play(gameController)
                Record(gameController)
            }
        }
    }

    @Composable
    fun Info(gameController: GameController) {
        Text(
            text = "Information",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.screenWidth / 20).sp,
            color = colorResource(id = R.color.black),
            modifier = Modifier
                .padding(gameController.constants.padding.dp)
                .clickable {
                    gameController.screenController.goInfo(gameController)
                }
        )
    }

    @Composable
    fun Player(gameController: GameController) {
        Image(
            painter = painterResource(id = R.drawable.menu_rabbit),
            contentDescription = "",
            modifier = Modifier
                .size((gameController.constants.heightPlayer * 2).dp)
                .padding(gameController.constants.padding.dp)
        )
    }

    @Composable
    fun Play(gameController: GameController) {
        Text(
            text = "Play",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.screenWidth / 20).sp,
            color = colorResource(id = R.color.black),
            modifier = Modifier
                .padding(gameController.constants.padding.dp)
                .clickable {
                    gameController.screenController.startGame(gameController)
                }
        )
    }

    @Composable
    fun Record(gameController: GameController) {
        Text(
            text = "Record: ${gameController.variables.record}",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.screenWidth / 20).sp,
            color = colorResource(id = R.color.black),
            modifier = Modifier
                .padding(gameController.constants.padding.dp)
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