package ru.porcupine.paperplane

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
val ground = Color(0xFF00701C)

private val tree = Color(0xFF815D00)
private val cactus = Color(0xFF75BE2F)

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
private fun Player(size: ScreenSize, state: GameUI, modifier: Modifier) {
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
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxSize()
        )
    }
}

@Composable
private fun ObstacleBody(modifier: Modifier) {
    BoxWithConstraints(modifier) {
        val canvasModifier = Modifier
            .fillMaxSize()

        Canvas(canvasModifier) {

            drawRect(color = tree, topLeft = Offset((size.width-size.width/2)/2, size.height/10), size = Size(size.width/2, size.height-size.height/10))
            drawCircle(color = cactus, radius = size.width, center = Offset((size.width)/2, size.width))
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