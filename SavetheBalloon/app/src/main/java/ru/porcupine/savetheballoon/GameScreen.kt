package ru.porcupine.savetheballoon

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource

val skyColor = Color(0xFF74B9F5)
val lightGreen = Color(0xFF75B6E0)
val darkGreen = Color(0xFF276C3D)
val dust = Color(0xFFFF9800)

private val cactus = Color(0xFF75BE2F)
private val obstacBlack = Color(0xFF000000)
private val obstacleBorderWidth = 3.dp

@Composable
fun GameScreen(size: ScreenSize, context: Context) {
    val gameViewModel = remember { GameViewModel(size, context) }

    Surface {
        Column(Modifier.fillMaxSize()) {

            GameArea(size, gameViewModel, modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
            )

            Scoreboard(gameViewModel, modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
                context
            )

        }
    }
}

@Composable
fun GameArea(size: ScreenSize, viewModel: GameViewModel, modifier: Modifier) {

    val state = viewModel.state.observeAsState(GameUI.NotStarted)

    BoxWithConstraints(modifier) {
        LaunchedEffect(viewModel) {
            viewModel.onGameBoundsSet(maxWidth.value, maxHeight.value)
        }

        Box(Modifier
            .clickable(remember { MutableInteractionSource() }, indication = null, onClick = viewModel::onTap)
            .fillMaxSize()
        ) {
            val currentState = state.value
            val playerAlignment = currentState.getPlayerAlignment()
            Player(size, currentState, Modifier.align(playerAlignment))

            currentState.getObstacles().forEach { obstacle ->
                DrawObstacle(Modifier
                    .size(obstacle.widthDp.dp, obstacle.heightDp.dp)
                    .graphicsLayer {
                        rotationX = when (obstacle.orientation) {
                            ObstaclePosition.Up -> 180f
                            ObstaclePosition.Down -> 0f
                        }
                    }
                    .absoluteOffset(
                        x = obstacle.leftMargin.dp,
                        y = obstacle.topMargin.dp
                    )
                )
            }

            if(currentState !is GameUI.Playing) {
                Text("T A P  T O  P L A Y", Modifier.align(
                    BiasAlignment(verticalBias = -0.3f, horizontalBias = 0f)
                ))
            }
        }
    }
}

private fun GameUI.getPlayerAlignment() = when(this) {
    is GameUI.NotStarted -> Alignment.Center
    is GameUI.Finished -> Alignment.BottomCenter
    is GameUI.Playing -> BiasAlignment(verticalBias = player.verticalBias, horizontalBias = 0f)
}

private fun GameUI.getObstacles(): List<Obstacle> = when(this) {
    is GameUI.Playing -> obstacles
    else -> emptyList()
}

@Composable
private fun Player(size:ScreenSize, state: GameUI, modifier: Modifier) {
    val playerRotation = when(state) {
        is GameUI.NotStarted,
        is GameUI.Finished -> 0f
        is GameUI.Playing -> state.player.rotation
    }
    Image(
        painter = painterResource(id = R.drawable.player),
        contentDescription = "Player icon",
        modifier = modifier
            .size((size.screenWidth/5).dp)
            .graphicsLayer { rotationZ = playerRotation }
    )
}

@Composable
fun DrawObstacle(modifier: Modifier) {
    Box(modifier) {
        ObstacleBody(
            ObstacleBorderMode.Sides, modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize()
        )
    }
}

private enum class ObstacleBorderMode { Sides }

@Composable
private fun ObstacleBody(borderMode: ObstacleBorderMode, modifier: Modifier) {
    BoxWithConstraints(modifier) {
        val canvasModifier = Modifier
            .fillMaxSize()

        Canvas(canvasModifier) {
            fun line(color: Color, width: Float, height: Float, offsetX: Float, offsetY: Float) {
                drawRect(color, size = Size(width, height), topLeft = Offset(offsetX, offsetY))
            }

            drawRect(color = cactus, topLeft = Offset(0f, size.height/50))

            var offset = 0
                while(size.height/15*offset<=size.height*1.1) {
                    var offsetX = 3
                    while (offsetX<=15) {
                        line(
                            obstacBlack,
                            size.width / 18,
                            size.height / 15,
                            offsetX = size.width / 18 * offsetX,
                            offsetY = size.height / 15 * offset
                        )
                        offsetX+=3
                    }
                        offset += 3
                }

            if (borderMode == ObstacleBorderMode.Sides) {
                val borderWidth = obstacleBorderWidth.toPx()
                var offsetBorder = 0f
                while(size.height/15*offsetBorder<=size.height*1.1) {
                    line(
                        obstacBlack,
                        borderWidth,
                        size.height / 15,
                        offsetX = -borderWidth,
                        offsetY = size.height/15 * offsetBorder
                    )
                    line(
                        obstacBlack,
                        borderWidth,
                        size.height / 15,
                        offsetX = size.width,
                        offsetY = size.height/15 * offsetBorder
                    )
                    offsetBorder+=3
                }
            }
        }
    }
}

@Composable
fun Scoreboard(gameViewModel: GameViewModel, modifier: Modifier, context: Context) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .padding(vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val scoreboard = gameViewModel.scoreBoard.observeAsState().value ?: Scoreboard(0,0)

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("SCORE", style = MaterialTheme.typography.subtitle1)
            Text("${scoreboard.current}", style = MaterialTheme.typography.body1)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("BEST", style = MaterialTheme.typography.subtitle1)
            Text("${scoreboard.best}", style = MaterialTheme.typography.body1)
        }

        SharedPreference(context).save("record", scoreboard.best)
    }
}