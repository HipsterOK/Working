package ru.porcupine.meteoriticrain.models

import ru.porcupine.meteoriticrain.activities.constants

class EnemyModel(
    var speed: Int,
    var x: Float = 0f,
    var y: Float = 0f,
) {
    fun spawn(){
        randomX()
        randomY()
    }

    private fun randomX(){
        this.x = (0..constants.screenWidth- constants.meteorWidth).random().toFloat()
    }

    private fun randomY(){
        this.y = (-constants.screenHeight..-constants.meteorHeight).random().toFloat()
    }
}