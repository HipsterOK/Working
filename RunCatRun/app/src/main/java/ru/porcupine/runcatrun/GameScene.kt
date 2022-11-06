package ru.porcupine.runcatrun

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.runcatrun.models.*
import ru.porcupine.runcatrun.ui.theme.*

var deviceWidthInPixels = 1920
var deviceHeightInPixels = 1920
var distanceBetweenEnemy = 100

private const val EARTH_GROUND_STROKE_WIDTH = 20f
val EARTH_Y_POSITION = deviceHeightInPixels.toFloat()- EARTH_GROUND_STROKE_WIDTH*10
private const val CLOUDS_SPEED = 1
private const val MAX_CLOUDS = 3
const val EARTH_OFFSET = 200
const val EARTH_SPEED = 9

var showBounds = mutableStateOf(false)

@Composable
fun MainGameScene(gameState: GameState, context:Context) {

    val cloudsState by remember { mutableStateOf(CloudState(maxClouds = MAX_CLOUDS, speed = CLOUDS_SPEED)) }
    val earthState by remember { mutableStateOf(EarthState(maxBlocks = 2, speed = EARTH_SPEED)) }
    val enemyState by remember { mutableStateOf(EnemyState(enemySpeed = EARTH_SPEED)) }
    val playerState by remember { mutableStateOf(PlayerState()) }
    val currentScore by gameState.currentScore.observeAsState()
    val highScore by gameState.highScore.observeAsState()

    val backColor = MaterialTheme.colors.backColor
    val earthColor = MaterialTheme.colors.earthColor
    val cloudsColor = MaterialTheme.colors.cloudColor
    MaterialTheme.colors.playerColor
    MaterialTheme.colors.enemyColor

    val vector = ImageVector.vectorResource(id = playerState.anim)
    val painter = rememberVectorPainter(image = vector)

    val vector1 = ImageVector.vectorResource(id = R.drawable.dog)
    val painter1 = rememberVectorPainter(image = vector1)

    if (!gameState.isGameOver)
    {
        gameState.increaseScore()
        cloudsState.moveForward()
        earthState.moveForward()
        enemyState.moveForward()
        playerState.move()

        enemyState.enemyList.forEach {
            if (playerState.getBounds()
                    .deflate(DOUBT_FACTOR)
                    .overlaps(
                        it.getBounds()
                            .deflate(DOUBT_FACTOR)
                    )
            ) {
                gameState.isGameOver = true
                val score = requireNotNull(gameState.currentScore.value)
                val high = requireNotNull(gameState.highScore.value)
                gameState._highScore.value = if (score > high) score else high
                SharedPreference(context).save("record", gameState._highScore.value!!)
                return@forEach
            }
        }
    }
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                    if (!gameState.isGameOver)
                        playerState.jump()
                    else {
                        enemyState.initEnemy()
                        playerState.init()
                        gameState.replay(context)
                    }
                },
        verticalArrangement = Arrangement.Bottom
    ) {
//        ShowBoundsSwitchView()
        HighScoreTextViews(requireNotNull(currentScore), requireNotNull(highScore))
        Canvas(modifier = Modifier.weight(1f)) {
            earthView(color = earthColor)
            cloudsView(cloudsState, color = cloudsColor)
            playerView(playerState, painter)
            enemyView(enemyState, painter1)
        }
    }
    GameOverTextView(
        modifier = Modifier
            .padding(top = 150.dp)
            .fillMaxWidth(),
        gameState.isGameOver
    )

}

fun DrawScope.playerView(playerState: PlayerState, painter: VectorPainter) {
    withTransform({
        translate(
            left = playerState.xPos,
            top = playerState.yPos - PLAYER_HEIGHT
        )
    }) {
        with(painter) {
                draw(Size(PLAYER_WIDTH, PLAYER_HEIGHT))
        }
        Log.w("Player", "$playerState.keyframe")

        drawBoundingBox(color = Color.Green, rect = Rect(topLeft = Offset(0f,0f),
        bottomRight = Offset(PLAYER_WIDTH, PLAYER_HEIGHT)))
    }
}

fun DrawScope.cloudsView(cloudState: CloudState, color: Color)
{
    cloudState.cloudsList.forEach { cloud ->
        withTransform({
            translate(
                left = cloud.xPos.toFloat(),
                top = cloud.yPos.toFloat()
            )
        })
        {
            drawPath(
                path = cloudState.cloudsList.first().path,
                color = color,
                style = Fill
            )

            drawBoundingBox(color = Color.Blue, rect = cloud.path.getBounds())
        }
    }
}

fun DrawScope.earthView(color: Color)
{
    drawLine(
        color = color,
        start = Offset(x = 0f, y = EARTH_Y_POSITION),
        end = Offset(x = deviceWidthInPixels.toFloat(), y = EARTH_Y_POSITION),
        strokeWidth = EARTH_GROUND_STROKE_WIDTH*40
    )
}

fun DrawScope.enemyView(enemyState: EnemyState, painter: VectorPainter)
{
    enemyState.enemyList.forEach { enemy ->
        withTransform({
            scale(enemy.scale, enemy.scale)
            translate(
                left = enemy.xPos.toFloat(),
                top = enemy.yPos - ENEMY_HEIGHT * enemy.scale
            )
        })
        {
            with(painter) {
                draw(Size(ENEMY_WIDTH, ENEMY_HEIGHT))
            }
            drawBoundingBox(color = Color.Red, rect = Rect(topLeft = Offset(0f,0f),
                bottomRight = Offset(ENEMY_WIDTH, ENEMY_HEIGHT)))
        }
    }
}

@Composable
fun HighScoreTextViews(currentScore: Int, highScore: Int)
{
    Spacer(modifier = Modifier.padding(top = 50.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "HIGH " +
                    "SCORE\n$highScore".padStart(5, '0'),
            style = TextStyle(color = MaterialTheme.colors.highScoreColor),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(start = 10.dp))
        Text(
            text = "SCORE\n$currentScore".padStart(5, '0'),
            style = TextStyle(color = MaterialTheme.colors.currentScoreColor),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ShowBoundsSwitchView()
{
    Spacer(modifier = Modifier.padding(top = 20.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(text = "Show Bounds")
        Spacer(modifier = Modifier.padding(start = 10.dp))
        Switch(checked = showBounds.value, onCheckedChange = {
            showBounds.value = it
        })
    }
}

@Composable
fun GameOverTextView(modifier: Modifier = Modifier, isGameOver: Boolean = true)
{
    Column(modifier = modifier) {
        Text(
            text = if (isGameOver) "GAME OVER" else "",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            letterSpacing = 5.sp,
            style = TextStyle(
                color = MaterialTheme.colors.gameOverColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        if (isGameOver) {
            Icon(
                painter = painterResource(id = R.drawable.ic_replay),
                contentDescription = null, // decorative element
                modifier = Modifier
                    .size(40.dp)
                    .padding(top = 10.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }
    }
}


fun DrawScope.drawBoundingBox(color: Color, rect: Rect, name: String? = null) {
    name?.let { Log.w("drawBounds", "$name $rect") }
    if (showBounds.value)
    {
        drawRect(color, rect.topLeft, rect.size, style = Stroke(3f))
        drawRect(
            color,
            rect.deflate(DOUBT_FACTOR).topLeft,
            rect.deflate(DOUBT_FACTOR).size,
            style = Stroke(
                width = 3f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(2f, 4f), 0f)
            )
        )
    }
}

