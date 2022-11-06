package ru.porcupine.monstercatcher.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import ru.porcupine.monstercatcher.R
import ru.porcupine.monstercatcher.data.Constants

class PlayerModel(
    val constants: Constants,
    var xPos:MutableState<Float> = mutableStateOf((constants.screenWidth-constants.playerSize)/2),
    var image:Int = R.drawable.gun,
    var direction:Direction = Direction.NULL,
) {


    fun moveRight(){
            if(xPos.value<constants.screenWidth-constants.playerSize && direction == Direction.RIGHT) {
                xPos.value+=constants.speed
            }
    }

    fun moveLeft(){
            if(xPos.value>0 && direction == Direction.LEFT) {
                xPos.value-=constants.speed
            }
    }
}

enum class Direction {
    LEFT, NULL, RIGHT
}