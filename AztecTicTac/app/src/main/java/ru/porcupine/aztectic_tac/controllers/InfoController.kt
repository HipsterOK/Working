package ru.porcupine.aztectic_tac.controllers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class InfoController(gameController: GameController) {
    var alpha by mutableStateOf(0f)
    var posY by mutableStateOf(gameController.constants.screenHeight)
}
