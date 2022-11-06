package ru.porcupine.dartsvsballoons.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.porcupine.dartsvsballoons.data.Constants

class ScreenController(private val constants: Constants){

    var gamePos by mutableStateOf(constants.screenWidth)
    var menuPos by mutableStateOf(0f)
    var resultPos by mutableStateOf(constants.screenWidth)

    fun goMenu(){
        menuPos = 0f
        gamePos = constants.screenWidth
        resultPos = constants.screenWidth
    }

    fun goGame(gameController: GameController){
        menuPos = constants.screenWidth
        gamePos = 0f
        resultPos = constants.screenWidth
        gameController.score=0
        gameController.game=true
        gameController.enemyModels.forEach {
            it.respawn()
        }
        gameController.bullets.forEach {
            it.destroy()
        }
        gameController.game()
    }

    fun goResult(gameController: GameController){
        menuPos = constants.screenWidth
        gamePos = constants.screenWidth
        resultPos = 0f
        gameController.updateRecord(gameController.context)
    }
}