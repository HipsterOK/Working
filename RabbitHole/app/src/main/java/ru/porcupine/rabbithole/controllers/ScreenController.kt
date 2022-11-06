package ru.porcupine.rabbithole.controllers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.*
import kotlin.concurrent.timerTask

class ScreenController(gameController: GameController) {

    var resultX by mutableStateOf(gameController.constants.screenWidth)
    var menuX by mutableStateOf(0f)
    var gameX by mutableStateOf(gameController.constants.screenWidth)
    var infoX by mutableStateOf(gameController.constants.screenWidth)

    fun gameOver(gameController: GameController) {
        resultX = 0f
        Timer().schedule(timerTask {
            gameX = gameController.constants.screenWidth
            infoX = gameController.constants.screenWidth
            menuX = gameController.constants.screenWidth
        }, 500)
    }

    fun startGame(gameController: GameController) {
        gameController.newGame()
        gameController.initGamePos()
        gameX = 0f
        Timer().schedule(timerTask {
            resultX = gameController.constants.screenWidth
            infoX = gameController.constants.screenWidth
            menuX = gameController.constants.screenWidth
        }, 500)
    }

    fun goMenu(gameController: GameController) {
        menuX = 0f
        Timer().schedule(timerTask {
            gameX = gameController.constants.screenWidth
            infoX = gameController.constants.screenWidth
            resultX = gameController.constants.screenWidth
        }, 500)
    }

    fun goInfo(gameController: GameController) {
        infoX = 0f
        Timer().schedule(timerTask {
            gameX = gameController.constants.screenWidth
            resultX = gameController.constants.screenWidth
            menuX = gameController.constants.screenWidth
        }, 500)
    }
}