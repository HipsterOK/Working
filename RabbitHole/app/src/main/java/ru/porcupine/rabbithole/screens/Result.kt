package ru.porcupine.rabbithole.screens

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
import ru.porcupine.rabbithole.R
import ru.porcupine.rabbithole.controllers.GameController

class Result {
    @Composable
    fun ResultContent(gameController: GameController) {
        val pos by animateDpAsState(targetValue = gameController.screenController.resultX.dp)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(pos, 0.dp)
        ) {
            Background()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(gameController.constants.padding.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Player(gameController)
                Play(gameController)
                Menu(gameController)
                Score(gameController)
                Record(gameController)
            }
        }
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
            text = "Play Again",
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
    fun Menu(gameController: GameController) {
        Text(
            text = "Menu",
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
    fun Score(gameController: GameController) {
        Text(
            text = "Score: ${gameController.variables.score}",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.screenWidth / 20).sp,
            color = colorResource(id = R.color.black),
            modifier = Modifier
                .padding(gameController.constants.padding.dp)
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