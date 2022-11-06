package ru.porcupine.smallhelicopter.controllers

import android.widget.ImageView
import ru.porcupine.smallhelicopter.activities.MainActivity
import ru.porcupine.smallhelicopter.activities.constants
import ru.porcupine.smallhelicopter.activities.playerAnimation
import ru.porcupine.smallhelicopter.models.PlayerModel
import ru.porcupine.smallhelicopter.models.Side

class PlayerController {
    val playerModel = PlayerModel()

    fun changeSide(playerImage: ImageView, mainActivity: MainActivity){
        if(playerModel.side == Side.RIGHT){
            playerModel.x= 0f
            playerModel.rotationY = 180f
            playerModel.side = Side.LEFT
        } else {
            playerModel.x = (+(constants.screenWidth)- playerAnimation.intrinsicWidth).toFloat()
            playerModel.rotationY = 0f
            playerModel.side= Side.RIGHT
        }
        mainActivity.updatePlayerUI(playerImage)
    }

}