package ru.porcupine.aztectic_tac.screens

import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.porcupine.aztectic_tac.R
import ru.porcupine.aztectic_tac.controllers.GameController
import ru.porcupine.aztectic_tac.utils.Intents
import java.util.*
import kotlin.concurrent.timerTask

class GameScreen {

    @Composable
    fun AreaCrystal(gameController: GameController, context: Context){
        Box(
            modifier = Modifier.fillMaxHeight(0.6f)
        ){
            Area()
            Column {
                Row {
                    Crystal(0, 0, gameController, context)
                    Crystal(0, 1, gameController, context)
                    Crystal(0, 2, gameController, context)
                }
                Row {
                    Crystal(1, 0, gameController, context)
                    Crystal(1, 1, gameController, context)
                    Crystal(1, 2, gameController, context)
                }
                Row {
                    Crystal(2, 0, gameController, context)
                    Crystal(2, 1, gameController, context)
                    Crystal(2, 2, gameController, context)
                }
            }
        }
    }

    @Composable
    fun Crystal(y:Int, x:Int, gameController:GameController, context: Context){
        val interactionSource = remember { MutableInteractionSource() }
        Image(
            painter = painterResource(id = gameController.imageCrystal[y][x]),
            contentDescription = "crystal",
            modifier = Modifier
                .size(
                    height = gameController.constants.crystalHeight.dp,
                    width = gameController.constants.crystalWidth.dp
                )
                .padding(gameController.constants.padding.dp)
                .clickable(interactionSource = interactionSource,
                    indication = null) {
                    if (gameController.gameEnable && gameController.turnPlayer && gameController.imageCrystal[y][x] == R.drawable.crystal_empty) {
                        gameController.turnPlayer = false
                        gameController.turnPlayer(y, x)
                        if (gameController.gameEnable && gameController.checkWin(R.drawable.crystal_red)) {
                            gameController.gameEnable = false
                            gameController.score++
                            gameController.winAlpha = 1f
                            gameController.winPosY = 0f
                        }
                        if (gameController.gameEnable && gameController.isTableFull()) {
                            gameController.gameEnable = false
                            gameController.drawAlpha = 1f
                            gameController.drawPosY = 0f
                        }
                        if (gameController.gameEnable) {
                            gameController.secondCharacter()
                            Timer().schedule(timerTask {
                                gameController.turnAI()
                                gameController.firstCharacter()
                                if (gameController.checkWin(R.drawable.crystal_purple)) {
                                    gameController.gameEnable = false
                                    Intents().final(context, gameController.score)
                                }
                                gameController.turnPlayer = true
                            }, 1000)
                        }
                    }
                }
        )
    }

    @Composable
    fun Area(){
        Image(
            painter = painterResource(id = R.drawable.area),
            contentDescription = "area",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.TopCenter
        )
    }

    @Composable
    fun Turn(image:Int, gameController: GameController, id:Int){
        Image(
            painter = painterResource(id = image),
            contentDescription = "character",
            modifier = Modifier
                .size(gameController.sizeCharacter[id].dp)
                .alpha(gameController.alphaCharacter[id])
        )
    }

    @Composable
    fun Win(gameController: GameController){
        val posY by animateDpAsState(targetValue = gameController.winPosY.dp)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .offset(0.dp, posY)
                .alpha(gameController.winAlpha)
                .padding(gameController.constants.padding.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.8f)
                    .padding(gameController.constants.padding.dp)
                    .background(Color.White, CircleShape)
            ){}
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Patterns().Text(
                    gameController = gameController,
                    text = "You win!",
                    h = 12f,
                    w = 2f,
                    font = 20f
                )
                Patterns().Button(
                    gameController = gameController,
                    text = "Next",
                    h = 12f,
                    w = 3f,
                    font = 20f
                ) {
                    gameController.restart()
                }
            }
        }
    }

    @Composable
    fun Draw(gameController: GameController){
        val posY by animateDpAsState(targetValue = gameController.drawPosY.dp)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .offset(0.dp, posY)
                .alpha(gameController.drawAlpha)
                .padding(gameController.constants.padding.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.8f)
                    .padding(gameController.constants.padding.dp)
                    .background(Color.White, CircleShape)
            ){}
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Patterns().Text(
                    gameController = gameController,
                    text = "Draw!",
                    h = 12f,
                    w = 2f,
                    font = 20f
                )
                Patterns().Button(
                    gameController = gameController,
                    text = "Replay",
                    h = 12f,
                    w = 3f,
                    font = 20f
                ) {
                    gameController.restart()
                }
            }
        }
    }
}