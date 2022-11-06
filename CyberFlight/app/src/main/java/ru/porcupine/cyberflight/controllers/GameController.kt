package ru.porcupine.cyberflight.controllers

import android.content.Context
import ru.porcupine.cyberflight.data.Const
import ru.porcupine.cyberflight.data.Enemy
import ru.porcupine.cyberflight.data.GameData
import ru.porcupine.cyberflight.data.Player

class GameController(context: Context) {
    val const = Const(context)
    val gameData = GameData()
    val player = Player(context)
    val enemy = Enemy()

    var gameActive = true

    var moveLeft = false
    var moveRight = false

}