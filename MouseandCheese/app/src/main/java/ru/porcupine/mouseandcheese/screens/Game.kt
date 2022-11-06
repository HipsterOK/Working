package ru.porcupine.mouseandcheese.screens

import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
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

class Game {
    @Composable
    fun GameContent(gameController: GameController, context: Context){
        val pos by animateDpAsState(targetValue = gameController.screenController.gameX.dp)
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
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Score(gameController = gameController)
                Hearts(gameController = gameController)
                GamePosies(gameController = gameController, context = context)
                Player(gameController = gameController)
            }
        }
    }

    @Composable
    fun Score(gameController: GameController){
        Text(
            text = gameController.variables.scoreView.toString(),
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.screenWidth / 20).sp,
            color = colorResource(id = R.color.black),
        )
    }

    @Composable
    fun Hearts(gameController: GameController){
        Row {
            for(i in 0 until 3){
                Heart(id = i, gameController = gameController)
            }
        }
    }

    @Composable
    fun Heart(id:Int, gameController: GameController){
        Image(
            painter = painterResource(id = R.drawable.heart),
            contentDescription = "",
            modifier = Modifier
                .size(gameController.constants.sizeHeart.dp)
                .alpha(gameController.variables.alphaHeart[id])
        )
    }

    @Composable
    fun GamePosies(gameController: GameController, context: Context){
        Column(
            modifier = Modifier
                .padding(gameController.constants.padding.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            for(i in 0 until 6){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (j in 0 until 4) {
                        GamePos(x =j , y = i, gameController = gameController, context)
                    }
                }
            }
        }
    }

    @Composable
    fun GamePos(x:Int, y:Int, gameController: GameController, context:Context){
        Image(
            painter = painterResource(id = gameController.variables.imageGamePos[y][x]),
            contentDescription ="",
            modifier = Modifier
                .size(gameController.constants.sizeGamePos.dp)
                .noRippleClickable {
                    if (gameController.variables.turn == y) {
                        gameController.openPosGame()
                        gameController.variables.turn--
                        gameController.checkWin(x, y, gameController)
                        gameController.updateCounter()
                        gameController.checkTurn()
                        gameController.updateRecord(context)
                    }
                }
        )
    }

    @Composable
    fun Player(gameController: GameController){
        Image(
            painter = painterResource(id = R.drawable.rabbit), 
            contentDescription = "",
            modifier = Modifier
                .size(width = gameController.constants.widthPlayer.dp, height = gameController.constants.heightPlayer.dp)
        )
    }

    @Composable
    fun Background(){
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }

    private inline fun Modifier.noRippleClickable(crossinline onClick: ()->Unit): Modifier = composed {
        clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick()
        }
    }
}