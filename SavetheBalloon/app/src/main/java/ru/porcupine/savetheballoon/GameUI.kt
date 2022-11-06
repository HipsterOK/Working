package ru.porcupine.savetheballoon

sealed class GameUI {

    object NotStarted: GameUI()

    data class Playing(
        val player: Player,
        val obstacles: List<Obstacle> = emptyList(),
        val score: Int
    ): GameUI()

    data class Finished(val finalScore: Int): GameUI()
}

data class Player(
    val sizeDp: Float,
    val rotation: Float,
    val verticalBias: Float
)

data class Obstacle(
    val widthDp: Float,
    val heightDp: Float,
    val topMargin: Float,
    val leftMargin: Float,
    val orientation: ObstaclePosition
)

enum class ObstaclePosition { Up, Down }

data class Scoreboard(
    val current: Int,
    val best: Int
)