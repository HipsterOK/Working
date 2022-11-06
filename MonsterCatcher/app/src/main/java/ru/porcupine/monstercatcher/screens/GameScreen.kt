package ru.porcupine.monstercatcher.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.monstercatcher.controller.GameController
import ru.porcupine.monstercatcher.models.Direction

class GameScreen{

    @Composable
    fun GameContent(gameController: GameController) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(gameController.screenController.gamePos.dp, 0.dp)
        ) {
            Bullets(gameController)
            Player(gameController)
            Enemies(gameController)
            Score(gameController)
            Controller(gameController)
        }
    }

    @Composable
    fun Player(gameController: GameController) {
        Image(
            painter = painterResource(gameController.playerModel.image),
            contentDescription = "",
            modifier = Modifier
                .size(gameController.constants.playerSize.dp)
                .offset(
                    y = (gameController.constants.screenHeight - gameController.constants.playerSize).dp,
                    x = (gameController.playerModel.xPos.value).dp
                )
        )
    }

    @Composable
    fun Enemies(gameController: GameController){
        for (i in 0 until 4){
            Enemy(id = i, gameController = gameController)
        }
    }

    @Composable
    fun Enemy(id:Int, gameController: GameController){
        Image(
            painter = painterResource(gameController.enemyModels[id].image),
            contentDescription = "",
            modifier = Modifier
                .size(gameController.constants.enemySize.dp)
                .offset(
                    y = gameController.enemyModels[id].yPos.value.dp,
                    x = gameController.enemyModels[id].xPos.value.dp
                )
        )
    }

    @Composable
    fun Controller(gameController: GameController){
        Row(modifier =
        Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                try {
                                    gameController.playerModel.direction = Direction.LEFT
                                    awaitRelease()
                                } finally {
                                    gameController.playerModel.direction = Direction.NULL
                                }
                            },
                        )
                    }
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                try {
                                    gameController.playerModel.direction = Direction.RIGHT
                                    awaitRelease()
                                } finally {
                                    gameController.playerModel.direction = Direction.NULL
                                }
                            },
                        )
                    }
            )
        }
    }

    @Composable
    fun Bullets(gameController: GameController){
        for (i in 0 until 10){
            Bullet(id = i, gameController = gameController)
        }
    }

    @Composable
    fun Bullet(id:Int, gameController: GameController){
        Image(
            painter = painterResource(gameController.bullets[id].image),
            contentDescription = "",
            modifier = Modifier
                .size(gameController.constants.bulletSize.dp)
                .offset(
                    y = gameController.bullets[id].yPos.value.dp,
                    x = gameController.bullets[id].xPos.value.dp
                )
                .alpha(gameController.bullets[id].alpha)
        )
    }

    @Composable
    fun Score(gameController: GameController){
        Text(
            text = "Score: ${gameController.score}",
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.textSize).sp,
            color = Color.White,
            modifier = Modifier
                .padding((gameController.constants.padding).dp)
        )
    }
}