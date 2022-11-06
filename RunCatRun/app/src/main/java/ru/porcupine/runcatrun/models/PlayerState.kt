package ru.porcupine.runcatrun.models

import androidx.compose.ui.geometry.Rect
import ru.porcupine.runcatrun.*

data class PlayerState(
    var xPos: Float = 60f,
    var yPos: Float = EARTH_Y_POSITION,
    var velocityY: Float = 0f,
    var gravity: Float = 0f,
    var scale: Float = 0.4f,
    var keyframe: Int = 0,
    private var animList: ArrayList<Int> = arrayListOf(),
    var isJumping: Boolean = false
)
{
    val anim: Int
        get() = if (keyframe <= 5) animList[0] else animList[1]

    init {
        animList.add(R.drawable.ic_player)
        animList.add(R.drawable.ic_player2)
    }

    fun init()
    {
        xPos = 60f
        yPos = EARTH_Y_POSITION
        velocityY = 0f
        gravity = 0f
        isJumping = false
    }

    fun move()
    {
        yPos += velocityY
        velocityY += gravity

        if (yPos > EARTH_Y_POSITION)
        {
            yPos = EARTH_Y_POSITION
            gravity = 0f
            velocityY = 0f
            isJumping = false
        }

        if (!isJumping)
        {
            changeKeyframe()
        }
    }

    fun jump()
    {
        if (yPos == EARTH_Y_POSITION)
        {
            isJumping = true
            velocityY = (-deviceHeightInPixels/35).toFloat()
            gravity = (deviceHeightInPixels/1000).toFloat()
        }
    }

    private fun changeKeyframe()
    {
        keyframe++
        if (keyframe == 10)
            keyframe = 0
    }

    fun getBounds() : Rect
    {
        return Rect(
            left = xPos,
            top = yPos - PLAYER_HEIGHT,
            right = xPos + PLAYER_WIDTH,
            bottom = yPos
        )
    }
}