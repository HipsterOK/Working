package ru.porcupine.monstercatcher.models

import androidx.compose.runtime.MutableState
import ru.porcupine.monstercatcher.controller.GameController
import ru.porcupine.monstercatcher.data.Constants
import java.util.Random

class EnemyModel(
    private val constants: Constants,
    var yPos: MutableState<Float>,
    var xPos: MutableState<Float>,
    val image: Int,
) {
    fun move(gameController: GameController){
        yPos.value+=constants.speed
        if (yPos.value>constants.screenHeight) {
            gameController.game=false
            gameController.screenController.goResult(gameController)
        }
    }

    fun respawn() {
//        alpha.value = 0f
            yPos.value = (Random().nextFloat() + 1) * (- constants.enemySize)
            xPos.value = Random().nextFloat() * (constants.screenWidth - constants.enemySize)
    }
}