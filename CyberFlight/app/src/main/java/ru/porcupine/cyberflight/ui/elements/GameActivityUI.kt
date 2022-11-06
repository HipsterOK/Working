package ru.porcupine.cyberflight.ui.elements

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.porcupine.cyberflight.R
import ru.porcupine.cyberflight.controllers.GameController

class GameActivityUI {
    @Composable
    fun Score(gameController: GameController){
        Box(contentAlignment = Alignment.TopEnd, modifier = Modifier
            .fillMaxSize()
            .padding(gameController.const.padding.dp)) {
            Default().TemplateText(
                gameController = gameController,
                text = "${gameController.gameData.score}",
                h = 13f,
                w = 6f,
                font = 20f
            )
        }
    }

    @Composable
    fun PlayerPlane(gameController: GameController){

        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(id = R.drawable.airplane),
                contentDescription = "plane",
                modifier = Modifier
                    .size(
                        width = gameController.const.widthPlane.dp,
                        height = gameController.const.heightPlane.dp
                    )
                    .absoluteOffset(
                        x = gameController.player.positionX.dp,
                        y = gameController.const.playerPositionY.dp
                    )
            )
        }
    }

    @Composable
    fun Controller(gameController: GameController){
        Row(modifier = Modifier.fillMaxSize()){
            Box(
                Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                try {
                                    gameController.moveLeft = true
                                    awaitRelease()
                                } finally {
                                    gameController.moveLeft = false
                                }
                            },
                        )
                    }
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
                    .alpha(0f)
//                    .background(Color.Cyan)
            )
            Box(
                Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                try {
                                    gameController.moveRight = true
                                    // Start recording here
                                    awaitRelease()
                                } finally {
                                    gameController.moveRight = false
                                    // Stop recording here
                                }
                            },
                        )
                    }
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .alpha(0f)
//                    .background(Color.Cyan)
            )

        }
    }

    @Composable
    fun Enemies(gameController: GameController){
        for(i in 0 until 6){
            Enemy(gameController, i)
        }
    }

    @Composable
    fun Enemy(gameController: GameController, id:Int){
        val posY by animateDpAsState(targetValue = gameController.enemy.enemyPositionY[id].dp, tween(easing = LinearEasing))
        val angle by animateFloatAsState(targetValue = gameController.enemy.angle[id].toFloat(), tween(easing = LinearEasing))
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(id = R.drawable.meteor),
                contentDescription = "meteor",
                modifier = Modifier
                    .size(
                        gameController.const.meteorSize.dp
                    )
                    .absoluteOffset(
                        x = gameController.enemy.enemyPositionX[id].dp,
                        y = posY
                    )
                    .rotate(angle)
                    .alpha(gameController.enemy.enemyAlpha[id])
//                    .background(Color.Blue)
            )
        }
    }
}