package ru.porcupine.paperplane

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class Game(
    private val size: ScreenSize,
    private val playerSize: Float = size.screenWidth/5,
    private val playerJumpingVelocity: Float = 2.8f,
    private val frameDelayMillis: Long = 60
) {

    private var time = 0f
    private var initialY = 0f
    private var currentBias = 0f
    private var currentRotation = 0f
    private var currentScore = 0

    private var boundsWidth = 0f
    private var boundsHeight = 0f

    private val player = Object(width = playerSize, height = playerSize, centerX = 0f, centerY = 0f)
    private var obstacles = listOf<Object>()

    fun setBounds(widthDp: Float, heightDp: Float) {
        boundsWidth = widthDp
        boundsHeight = heightDp
    }

    suspend fun start(): Flow<GameUI> {
        initGameValues()
        return flow {
            if(boundsHaveNotBeenSet()) {
                emit(GameUI.NotStarted)
                return@flow
            }
            while(true) {
                if(thereAreAnyCollisions() || playerIsOutOfBounds()) {
                    emit(GameUI.Finished(1))
                    break
                } else {
                    emit(buildCurrentState())
                }
                delay(frameDelayMillis)
                move()
            }
        }
    }

    fun jump() {
        this.time = 0f
        this.initialY = currentBias
        this.currentRotation = size.GOING_UP
    }

    private fun initGameValues() {
        this.time = 0f
        this.initialY = size.CENTER
        this.currentRotation = size.GOING_UP
        this.currentScore = 0

        this.player.centerX = boundsWidth / 2
        this.player.centerY = boundsHeight / 2
        this.obstacles = (0..6).map {
            val width = size.OBSTACLE_WIDTH
            val height = Random.nextInt((size.MIN_OBSTACLE_HEIGHT /10).toInt(),
                (size.MAX_OBSTACLE_HEIGHT /10).toInt()
            ) * 10f
            val up = Random.nextBoolean()
            val centerX = boundsWidth + size.OBSTACLE_WIDTH + size.OBSTACLE_WIDTH * 1.5f * it
            val centerY = if(up) height / 2f else boundsHeight - height / 2f
            Object(width, height, centerX, centerY)
        }
    }

    private fun move() {
        time += 0.05f
        movePlayer()
        moveObstacles()
    }

    private fun movePlayer() {
        val movement = -4.9f * time * time + playerJumpingVelocity * time
        val newBias = initialY - movement

        currentRotation = if(newBias < currentBias) { // is going up
            size.GOING_UP
        } else {
            (currentRotation + time * 30).coerceAtMost(size.FACING_DOWN)
        }
        currentBias = newBias
        player.centerY = newBias.asAbsoluteY()
    }

    private fun moveObstacles() {
        val lastX = obstacles.maxOf { it.centerX }
        val middleScreen = boundsWidth / 2
        obstacles.forEach {
            val newCenterX = it.centerX - size.OBSTACLE_WIDTH / 10f
            if(it.centerX > middleScreen && newCenterX < middleScreen) {
                currentScore += 1
            }
            it.centerX = newCenterX
            val isOutside = (it.centerX + it.width / 2f) < 0f
            if(isOutside) {
                it.centerX = lastX + size.OBSTACLE_WIDTH * 1.5f
            }
        }
    }

    private fun thereAreAnyCollisions(): Boolean {
        return obstacles.any { obstacle -> obstacle collidesWith player }
    }

    private fun playerIsOutOfBounds(): Boolean {
        return (player.centerY + playerSize / 2) > boundsHeight || (player.centerY + playerSize / 2)<0
    }

    private fun boundsHaveNotBeenSet(): Boolean {
        return boundsWidth == 0f && boundsHeight == 0f
    }

    private fun buildCurrentState() = GameUI.Playing(
        player = Player(
            sizeDp = player.width,
            verticalBias = currentBias,
            rotation = currentRotation,
        ),
        obstacles = obstacles
            .filter { it.isVisible() }
            .map {
                Obstacle(
                    widthDp = it.width,
                    heightDp = it.height,
                    topMargin = it.centerY - it.height / 2f,
                    leftMargin = it.centerX - it.width / 2f,
                    orientation = if (it.centerY > boundsHeight / 2) ObstaclePosition.Down else ObstaclePosition.Up
                )
            },
        score = currentScore
    )

    private fun Float.asAbsoluteY(): Float {
        return boundsHeight * (this + 1) / 2
    }

    private fun Object.isVisible(): Boolean {
        return (centerX + width / 2f) > 0f
    }
}

private data class Object(
    val width: Float,
    val height: Float,
    var centerX: Float,
    var centerY: Float
) {

    infix fun collidesWith(other: Object): Boolean {
        val tolerance = 20
        if (right + tolerance <= other.left || other.right - tolerance <= left)
            return false
        if (bottom - tolerance <= other.top || other.bottom - tolerance <= top)
            return false
        return true
    }

    val left get() = centerX - width / 2f
    val right get() = centerX + width / 2f
    val top get() = centerY - height / 2f
    val bottom get() = centerY + height / 2f

}