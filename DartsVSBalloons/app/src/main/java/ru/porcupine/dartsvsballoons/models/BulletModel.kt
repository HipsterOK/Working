package ru.porcupine.dartsvsballoons.models

import android.util.Log
import androidx.compose.runtime.MutableState
import ru.porcupine.dartsvsballoons.R
import ru.porcupine.dartsvsballoons.controller.GameController
import ru.porcupine.dartsvsballoons.data.Constants

class BulletModel(
    private val constants: Constants,
    var yPos: MutableState<Float>,
    var xPos: MutableState<Float>,
    val image:Int = R.drawable.bullet,
    var alpha: Float = 0f,
    private var isCreated:Boolean = false,
    val playerModel: PlayerModel,
    val enemyModels: List<EnemyModel>,
) {
    fun move(gameController: GameController){
        if(isCreated) {
            yPos.value -= constants.speed
            checkCollision(gameController)
        }
        if(yPos.value<-constants.bulletSize){
            destroy()
        }
    }

    fun spawn(){
        xPos.value=(playerModel.xPos.value+constants.playerSize/2-constants.bulletSize/2)
        yPos.value=constants.screenHeight-constants.playerSize
        isCreated=true
        alpha=1f
    }

    fun destroy(){
        isCreated=false
        alpha = 0f
    }

    private fun checkCollision(gameController: GameController){
        val leftSideBullet = this.xPos.value
        val rightSideBullet = this.xPos.value+constants.bulletSize
        val topSideBullet = this.yPos.value
        val bottomSideBullet = this.yPos.value+constants.bulletSize

        enemyModels.forEach {
            Log.i("left1", this.yPos.value.toString())
            Log.i("left2", "${getBotSideEnemy(enemyModels[0])}<${bottomSideBullet}<${getTopSideEnemy(enemyModels[0])}")
            if(
                ((leftSideBullet in getLeftSideEnemy(it)..getRightSideEnemy(it)) ||
                (rightSideBullet in getLeftSideEnemy(it)..getRightSideEnemy(it))) &&
                ((topSideBullet in getBotSideEnemy(it)..getTopSideEnemy(it)) ||
                        (bottomSideBullet in getBotSideEnemy(it)..getTopSideEnemy(it)))
            ){
                it.respawn()
                this.destroy()
                gameController.score++
                Log.i("score", gameController.score.toString())
            }
        }
    }

    private fun getLeftSideEnemy(enemyModel: EnemyModel): Float {
        return enemyModel.xPos.value
    }

    private fun getRightSideEnemy(enemyModel: EnemyModel): Float {
        return enemyModel.xPos.value+constants.enemySize
    }

    private fun getTopSideEnemy(enemyModel: EnemyModel): Float {
        return enemyModel.yPos.value+constants.enemySize
    }

    private fun getBotSideEnemy(enemyModel: EnemyModel): Float {
        return enemyModel.yPos.value
    }
}