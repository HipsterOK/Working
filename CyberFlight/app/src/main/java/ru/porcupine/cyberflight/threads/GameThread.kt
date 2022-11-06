package ru.porcupine.cyberflight.threads

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.cyberflight.controllers.GameController

class GameThread {
    fun startThreadScore(scope: CoroutineScope, gameController: GameController){
        scope.launch {
            while (gameController.gameActive){
                delay(1000)
                gameController.gameData.score++
                if(gameController.enemy.delaySpeed>10) {
                    gameController.enemy.delaySpeed -= 1
                }
            }
        }
    }

     fun startThreadMove(scope: CoroutineScope, gameController: GameController) {
         scope.launch {
             while (gameController.gameActive) {
                 if (gameController.moveLeft && gameController.player.positionX>gameController.const.moveSpeed) {
                     gameController.player.positionX -= gameController.const.moveSpeed
                 }
                 if (gameController.moveRight && gameController.player.positionX<gameController.const.screenWidth-gameController.const.widthPlane- gameController.const.moveSpeed) {
                     gameController.player.positionX += gameController.const.moveSpeed
                 }
                 delay(10)
             }
         }
     }

    fun startThreadMeteor(context: Context, scope: CoroutineScope, gameController: GameController){
        scope.launch {
            gameController.enemy.randomPositionEnemy(context)

            while (gameController.gameActive){
                for(i in 0 until 6) {
                    gameController.enemy.angle[i]++
                    gameController.enemy.enemyPositionY[i] += 5f
                    gameController.enemy.checkPosition(gameController,i,context)
                }
                delay(gameController.enemy.delaySpeed)
            }
        }
    }
}