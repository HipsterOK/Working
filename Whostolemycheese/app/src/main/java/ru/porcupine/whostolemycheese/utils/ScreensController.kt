package ru.porcupine.whostolemycheese.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ScreensController(private val controller: Controller) {
    var gamePos by mutableStateOf(controller.constants.screenWidth)
    var gameAlpha by mutableStateOf(0f)
    var menuPos by mutableStateOf(0f)
    var resultPos by mutableStateOf(controller.constants.screenWidth)
    var infoPos by mutableStateOf(controller.constants.screenWidth)

    fun game(){
        gamePos=0f
        gameAlpha=1f
        menuPos=controller.constants.screenWidth
        resultPos=controller.constants.screenWidth
        infoPos=controller.constants.screenWidth
    }

    fun menu(){
        gamePos=controller.constants.screenWidth
        gameAlpha=0f
        menuPos=0f
        resultPos=controller.constants.screenWidth
        infoPos=controller.constants.screenWidth
    }

    fun info(){
        gamePos=controller.constants.screenWidth
        gameAlpha=0f
        menuPos=controller.constants.screenWidth
        resultPos=controller.constants.screenWidth
        infoPos=0f
    }

    fun result(){
        gamePos=controller.constants.screenWidth
        gameAlpha=0f
        menuPos=controller.constants.screenWidth
        resultPos=0f
        infoPos=controller.constants.screenWidth
    }
}