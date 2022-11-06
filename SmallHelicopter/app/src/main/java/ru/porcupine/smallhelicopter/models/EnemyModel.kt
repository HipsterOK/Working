package ru.porcupine.smallhelicopter.models

import android.util.Log
import android.widget.ImageView
import ru.porcupine.smallhelicopter.controllers.GameController
import ru.porcupine.smallhelicopter.activities.constants

class EnemyModel(
    private val id:Int,
    var x: Float = 0f,
    var y: Float = -0f,
    var rotationY:Float = 180f,
    var side: Side = Side.LEFT
){
    private fun randomSide(){
        this.side = if((0..1).random()==0){
            Side.LEFT
        } else Side.RIGHT
    }

    private fun setSide(enemyImage: ImageView){
        if(this.side== Side.LEFT){
            this.x = 0f
            this.rotationY = 180f
        } else {
            this.x= ((constants.screenWidth)-enemyImage.width).toFloat()
            this.rotationY = 0f
        }
        Log.i("side", this.side.toString())
    }

    private fun randomY(){
        this.y= (-400*(id+1)).toFloat()
    }

    fun spawn(enemyImage: ImageView){
        this.randomSide()
        this.setSide(enemyImage)
        this.randomY()
    }

    fun checkCollision(
        enemyImages: MutableList<ImageView>,
        playerImage: ImageView,
        gameController: GameController
    ){
        if(this.y + enemyImages[id].height/3 > constants.screenHeight-playerImage.height.toFloat() &&
            this.y+enemyImages[id].height - enemyImages[id].height/3 < constants.screenHeight-playerImage.height/3 &&
            this.side == gameController.playerController.playerModel.side){
            gameController.gameEnbl = false
            Log.e("collision", "${this.y} -- ${constants.screenHeight-playerImage.height.toFloat()}")
            Log.e("collision", "${this.side} == ${gameController.playerController.playerModel.side}")

        }
    }
}