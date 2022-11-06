package ru.porcupine.buildapedestal.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import ru.porcupine.buildapedestal.other.Composablies
import ru.porcupine.buildapedestal.other.GameController
import ru.porcupine.buildapedestal.R
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameController = GameController(this)
        gameController.movePedestal(lifecycleScope)

        setContent {
            Content(gameController)
        }
    }

    @Composable
    private fun Content(gameController: GameController) {
        Composablies().Background(back = R.drawable.game_background)
        Game(gameController)
        Score(gameController)
        TapScreen(gameController)
    }

    @Composable
    private fun Score(gameController: GameController) {
        Box(
            Modifier
                .fillMaxSize()
        ) {
            Box(
                Modifier
                    .fillMaxWidth(0.5f).align(Alignment.BottomStart),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.text_border),
                    contentDescription = "player border",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(
                            height = (gameController.scoreHeight).dp,
                            width = (gameController.screenWidth / 3).dp
                        ),
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    text = gameController.score.toString(),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontFamily = FontFamily(Font(gameController.font))),
                    fontSize = (gameController.screenWidth / 20).sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box(
                Modifier
                    .fillMaxWidth().align(Alignment.BottomEnd),
            ) {
                Row(
                    Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hp),
                        contentDescription = "player border",
                        modifier = Modifier
                            .size(
                                (gameController.screenWidth / 7).dp
                            )
                            .alpha(gameController.hpView[0]),
                        contentScale = ContentScale.Inside
                    )

                    Image(
                        painter = painterResource(id = R.drawable.hp),
                        contentDescription = "player border",
                        modifier = Modifier
                            .size(
                                (gameController.screenWidth / 7).dp
                            )
                            .alpha(gameController.hpView[1]),
                        contentScale = ContentScale.Inside
                    )

                    Image(
                        painter = painterResource(id = R.drawable.hp),
                        contentDescription = "player border",
                        modifier = Modifier
                            .size(
                                (gameController.screenWidth / 7).dp
                            )
                            .alpha(gameController.hpView[2]),
                        contentScale = ContentScale.Inside
                    )
                }
            }
        }
    }

    @Composable
    private fun Game(gameController: GameController) {
        PedestalTop(gameController)
        for(i in 0 until 4) {
            PedestalMiddle(gameController, i)
        }
        PedestalBottom(gameController)
    }

    @Composable
    private fun PedestalTop(gameController: GameController) {
        val posY by animateDpAsState(targetValue = gameController.pedestalTopPosY.dp)
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.pedestal_top),
                contentDescription = "pedestal_top",
                modifier = Modifier
                    .size(
                        width = gameController.pedestalSize.dp,
                        height = (gameController.pedestalSize * 2.5).dp
                    )
                    .offset(
                        x = gameController.pedestalTopPosX.dp,
                        y = posY
                    )
            )
        }
    }

    @Composable
    private fun PedestalMiddle(gameController: GameController, index: Int) {
        val posX by animateDpAsState(targetValue = gameController.pedestalMiddlePosX[index].dp)
        val posY by animateDpAsState(targetValue = gameController.pedestalMiddlePosY[index].dp, )
        val angle by animateFloatAsState(targetValue = gameController.pedestalAngle[index].toFloat())
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.pedestal_center),
                contentDescription = "pedestal_center",
                modifier = Modifier
                    .size(gameController.pedestalSize.dp)
                    .offset(
                        x = posX,
                        y = posY
                    )
                    .rotate(angle)
            )
        }
    }

    @Composable
    private fun PedestalBottom(gameController: GameController) {
        val posY by animateDpAsState(targetValue = gameController.pedestalBottomPosY.dp)
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = gameController.bottomImg),
                contentDescription = "pedestal_bottom",
                modifier = Modifier
                    .size(gameController.pedestalSize.dp)
                    .offset(
                        x = gameController.pedestalBottomPosX.dp,
                        y = posY
                    )
            )
        }
    }

    @Composable
    private fun TapScreen(gameController: GameController) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0f)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = {
                        if (gameController.moving && gameController.gameEnable) {
                            gameController.pedestalMiddlePosY[gameController.currentIndex] =
                                (gameController.screenHeight - gameController.pedestalSize -
                                        gameController.padding -
                                        gameController.scoreHeight
                                        - (gameController.pedestalSize.toInt() * gameController.countHeight)).toInt()
                            gameController.moving = false
                            if (
                                gameController.pedestalMiddlePosX[gameController.currentIndex] + (gameController.pedestalSize / 4) > gameController.pedestalBottomPosX - (gameController.pedestalSize / 4) + gameController.pedestalSize ||
                                gameController.pedestalMiddlePosX[gameController.currentIndex] - (gameController.pedestalSize / 4) + gameController.pedestalSize < gameController.pedestalBottomPosX + (gameController.pedestalSize / 4)
                            ) {
                                val index = gameController.currentIndex
                                Timer().schedule(timerTask {
                                    gameController.pedestalAngle[index] = 360
                                    gameController.pedestalMiddlePosY[index] =
                                        (gameController.screenHeight + gameController.pedestalSize).toInt()
                                }, 100)
                                Timer().schedule(timerTask {
                                    gameController.pedestalMiddlePosX[index] =
                                        gameController.screenWidth
                                    gameController.hp--
                                    gameController.hpView[gameController.hp] = 0f
                                    if(gameController.hp == 0){
                                        gameController.moving = false
                                        gameController.gameEnable = false
                                        gameController.gameOver(this@MainActivity)
                                    }
                                }, 300)

                                Timer().schedule(timerTask {
                                    gameController.pedestalMiddlePosY[index] = 0
                                }, 500)

                            } else {
                                gameController.freePedestal[gameController.currentIndex] = false
                                gameController.countHeight++
                                gameController.score++
                            }
                            gameController.nextIndex()
                            for (i in 0 until 4) {
                                if (!gameController.freePedestal[gameController.currentIndex]) {
                                    gameController.nextIndex()
                                    gameController.tempCount++
                                } else break
                            }


                            if ((gameController.score % 4) == 0 && gameController.tempCount != 0) {
                                Timer().schedule(timerTask {
                                    gameController.upping()
                                    Timer().schedule(timerTask {
                                        gameController.moving = true
                                    }, 1000)
                                }, 700)

                            } else {
                                gameController.pedestalMiddlePosY[gameController.currentIndex] = 0
                                Timer().schedule(timerTask {
                                    gameController.moving = true
                                }, 1000)
                            }

                        }
                    }
                )
        )

    }
}

