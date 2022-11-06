package ru.porcupine.colorcommotion.controllers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.porcupine.colorcommotion.data.Constants

class ScreenController(val constants: Constants){

    var gamePos by mutableStateOf(constants.screenWidth)
    var menuPos by mutableStateOf(0f)
    var resultPos by mutableStateOf(constants.screenWidth)

    fun goMenu(){
        menuPos = 0f
        gamePos = constants.screenWidth
        resultPos = constants.screenWidth
    }

    fun goGame(gameController:GameController){
        menuPos = constants.screenWidth
        gamePos = 0f
        resultPos = constants.screenWidth
        gameController.initVar()
        gameController.startGame()
    }

    fun goResult(gameController: GameController){
        menuPos = constants.screenWidth
        gamePos = constants.screenWidth
        resultPos = 0f
        gameController.checkRecord(gameController.context)
    }
}