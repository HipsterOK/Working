package ru.porcupine.gravityguy.controllers

import android.util.Log
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.gravityguy.activities.MainActivity
import ru.porcupine.gravityguy.activities.constants
import ru.porcupine.gravityguy.activities.gameController
import ru.porcupine.gravityguy.models.EnemyModel

var speedFall = 5

class EnemyController {
    val enemyModels = mutableListOf(
        EnemyModel(0),
        EnemyModel(1),
        EnemyModel(2),
        EnemyModel(3),
        EnemyModel(4),
    )

    fun fall(enemyImages:MutableList<ImageView>, mainActivity: MainActivity, playerImage: ImageView){
        CoroutineScope(Dispatchers.IO).launch {
            while (true){
                delay(20)
                for(i in 0 until 5){
                    enemyModels[i].y+= speedFall
//                    enemyModels[i].y = 0f
                    if(enemyModels[i].y > constants.screenHeight){
                        enemyModels[i].spawn(enemyImages[i])
                    }
                    gameController.enemyController.enemyModels[i].checkCollision(enemyImages, playerImage, gameController)
//                    if(gameController.enemyController.enemyModels[i].y> constants.screenHeight-playerImage.height.toFloat()/4*3 && gameController.enemyController.enemyModels[i].y<constants.screenHeight - playerImage.height.toFloat()/4 && gameController.enemyController.enemyModels[i].side == gameController.playerController.playerModel.side) {
//                        gameController.gameEnbl = false
//                        Log.e(
//                            "collision",
//                            "${gameController.enemyController.enemyModels[i].y} -- ${constants.screenHeight - playerImage.height.toFloat()}"
//                        )
//                        Log.e(
//                            "collision",
//                            "${gameController.enemyController.enemyModels[i].side} == ${gameController.playerController.playerModel.side}"
//                        )
//                    }
                }
                Log.i("fall", enemyModels[0].side.toString())
                Log.i("fall", enemyModels[0].y.toString())
                Log.i("fall", enemyModels[0].x.toString())
                mainActivity.updateEnemyUI(enemyImages)
            }
        }
    }
}