package ru.porcupine.onecircletwocircle.graphics

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.porcupine.onecircletwocircle.R
import ru.porcupine.onecircletwocircle.controller.GameController

class MainActivityUI {
    @Composable
    fun Score(gameController: GameController){
        Box(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxSize().padding(gameController.padding.dp)) {
            Template().TemplateText(
                gameController = gameController,
                text = "Score: ${gameController.score}",
                h = 12f,
                w = 3f,
                font = 20f
            )
        }
    }

    @Composable
    fun PlayerCircles(gameController: GameController){
        Box(modifier = Modifier.fillMaxSize()){
            CenterCircles(gameController = gameController, id = 0)
            CenterCircles(gameController = gameController, id = 1)
            BottomCircles(gameController = gameController)
        }
    }

    @Composable
    private fun CenterCircles(gameController: GameController, id:Int){
        val pos by animateDpAsState(targetValue = (gameController.playerXPosition[id]).dp, tween(20, easing = LinearEasing))
        Image(
            painter = painterResource(id = R.drawable.green_circle),
            contentDescription = "moving circles",
            modifier = Modifier
                .size(gameController.circlesSize.dp)
                .absoluteOffset(
                    pos,
                    (gameController.screenHeight / 3 * 2).dp
                )
        )
    }

    @Composable
    private fun BottomCircles(gameController: GameController){
        Image(
            painter = painterResource(id = R.drawable.black_circle),
            contentDescription = "bottom circle",
            modifier = Modifier
                .size(gameController.circlesSize.dp)
                .absoluteOffset(
                    (gameController.centerXPosition).dp,
                    (gameController.screenHeight - gameController.circlesSize - gameController.padding).dp
                )
        )
    }

    @Composable
    fun FallCircles(gameController: GameController){
        Box(modifier = Modifier.fillMaxSize()) {
            for (i in 0 until 6) {
                FallCircle(gameController = gameController, id = i)
            }
        }
    }

    @Composable
    private fun FallCircle(gameController: GameController, id:Int){
        val pos by animateDpAsState(targetValue = gameController.fallYPosition[id].dp,tween(gameController.delay.toInt(), easing = LinearEasing))
        Image(
            painter = painterResource(id = gameController.fallImg[id]),
            contentDescription = "fall circle",
            modifier = Modifier
                .size(gameController.circlesSize.dp)
                .alpha(gameController.alpha[id])
                .absoluteOffset(
                    (gameController.centerXPosition).dp,
                    (pos)
                )
        )
    }

    @Composable
    fun Controller(gameController: GameController){
        Box(
            Modifier
                .fillMaxSize()
                .alpha(0f)
                .clickable{
                    if(gameController.gameActive) {
                        if (!gameController.playerPush) {
                            gameController.push()
                        } else gameController.unPush()
                    }
                }
        ){}
    }
}