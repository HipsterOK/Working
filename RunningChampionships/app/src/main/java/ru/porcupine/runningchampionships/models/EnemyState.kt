package ru.porcupine.runningchampionships.models

import android.util.Log
import androidx.compose.ui.geometry.Rect
import ru.porcupine.runningchampionships.*

data class EnemyState(
    val enemyList: ArrayList<EnemyModel> = ArrayList(),
    val enemySpeed: Int = EARTH_SPEED,
) {
    init {
        initEnemy()
    }

    fun initEnemy()
    {
        enemyList.clear()
        var startX = deviceWidthInPixels + 150
        val enemyCount = 3

        for (i in 0 until enemyCount) {
            val enemy = EnemyModel(
                count = rand(1, 3),
                scale = rand(0.85f, 1.2f),
                xPos = startX,
                yPos = (EARTH_Y_POSITION.toInt() + rand(20, 30))
            )
            Log.w("Enemy", "${enemy.xPos}")
            enemyList.add(enemy)

            startX += distanceBetweenEnemy
            startX += rand(0, distanceBetweenEnemy)
        }
    }

    fun moveForward()
    {
        enemyList.forEach { enemy ->
            enemy.xPos -= enemySpeed
        }

        if (enemyList.first().xPos < -250) {
            enemyList.removeAt(0)
            val enemy = EnemyModel(
                count = rand(1, 3),
                scale = rand(0.85f, 1.2f),
                xPos = nextEnemyX(enemyList.last().xPos),
                yPos = EARTH_Y_POSITION.toInt() + rand(20, 30)
            )
            enemyList.add(enemy)
            Log.e("Enemy", "${enemy.xPos}")
        }
    }

    private fun nextEnemyX(lastX: Int): Int
    {
        var nextX = lastX + distanceBetweenEnemy
        nextX += rand(0, distanceBetweenEnemy)
        if (nextX < deviceWidthInPixels)
            nextX += (deviceWidthInPixels - nextX)
        return nextX
    }
}

data class EnemyModel(
    val count: Int = 1,
    val scale: Float = 1f,
    var xPos: Int = 0,
    var yPos: Int = 0
) {

    fun getBounds() : Rect
    {
        return Rect(
            left = xPos.toFloat(),
            top = yPos.toFloat() - (ENEMY_HEIGHT * scale),
            right = xPos + (ENEMY_WIDTH * scale),
            bottom = yPos.toFloat()
        )
    }
}