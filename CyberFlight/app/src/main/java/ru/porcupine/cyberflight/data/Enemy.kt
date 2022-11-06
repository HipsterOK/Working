package ru.porcupine.cyberflight.data

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import ru.porcupine.cyberflight.controllers.ActivityController
import ru.porcupine.cyberflight.controllers.GameController
import java.util.*
import kotlin.concurrent.timerTask

class Enemy {

    var angle = mutableStateListOf(
        0,0,0,0,0,0
    )

    var enemyAlpha = mutableStateListOf(
        1f,1f,1f,1f,1f,1f
    )

    var delaySpeed = 75L

    var enemyPositionY = mutableStateListOf(
        0f,0f,0f,0f,0f,0f
    )

    var enemyPositionX = mutableStateListOf(
        0f,0f,0f,0f,0f,0f
    )

    fun randomPositionEnemy(context:Context){
        for(i in 0 until 6) {
            newEnemy(context, i)
        }
    }


    fun checkPosition(gameController: GameController, id:Int, context: Context){
        if(gameController.gameActive){
        if(gameController.enemy.enemyPositionY[id]<gameController.const.screenHeight+gameController.const.padding && gameController.gameActive) {
            if (
                (
                        (gameController.enemy.enemyPositionX[id] + gameController.const.deadZone <= gameController.player.positionX &&
                                gameController.player.positionX <= gameController.enemy.enemyPositionX[id] + gameController.const.meteorSize - gameController.const.deadZone)
                                ||
                                (gameController.enemy.enemyPositionX[id] + gameController.const.deadZone <= gameController.player.positionX + gameController.const.widthPlane &&
                                        gameController.player.positionX + gameController.const.widthPlane <= gameController.enemy.enemyPositionX[id] + gameController.const.meteorSize - gameController.const.deadZone)
                        )
                &&
                (gameController.enemy.enemyPositionY[id] >= gameController.const.screenHeight - gameController.const.heightPlane + gameController.const.padding - gameController.const.deadZone)
                &&
                (gameController.enemy.enemyPositionY[id] <= gameController.const.screenHeight - gameController.const.padding - gameController.const.deadZone)
            ) {
                gameController.gameActive=false
            }
        } else{
            gameController.enemy.newEnemy(context, id)
        }
        } else {
            ActivityController().activityResult(context, gameController.gameData.score)
        }
    }

    private fun newEnemy(context: Context, id: Int){
        enemyAlpha[id]=0f
        angle[id]=0
        enemyPositionX[id] = (0..(Const(context).screenWidth-Const(context).meteorSize).toInt()).random().toFloat()
        enemyPositionY[id] =  -Const(context).meteorSize*(2..20).random()
        Timer().schedule(timerTask {
            enemyAlpha[id]=1f
        },1000)
    }
}