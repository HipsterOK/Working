package ru.porcupine.taxidriver

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.*

class EnemyController(context: Context) {
    var enemyList = mutableListOf<Enemyes>()
    var enemyListSave = mutableListOf<Enemyes>()
    private val mainController = MainController(context)
    private val playerController = PlayerController(context)
    val enemyWidth = playerController.playerWidth
    val enemyHight = playerController.playerHeight
    var enemyY = mutableStateListOf(
        0,0,0,0,0,0
    )
        private set

    var enemyX = mutableStateListOf(
        0,0,0,0,0,0
    )
        private set
    var enable = mutableListOf(true, true, true, true, true, true)


    private fun createEnemyes(){
        enemyList = EnemyAdapter().fillEnemyes()
        enemyListSave = enemyList
    }

    private fun setX(){
        for(i in 0..5){
            if(enemyList[i].up){
                enemyX[i] = (((2..3).random()*mainController.screenWidth/4).toInt()+enemyWidth/3).toInt()
            } else enemyX[i] = (((0..1).random()*mainController.screenWidth/4).toInt()+enemyWidth/3).toInt()
        }
    }

    private fun setY(){
        for(i in 0..5){
            enemyY[i]=(((-mainController.screenHeight*3).toInt()..(-mainController.screenHeight).toInt()).random())
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun spawn(mainController: MainController, playerController: PlayerController){
        createEnemyes()
        setX()
        setY()
        GlobalScope.launch {
            while (mainController.gameEnbl) {
                whiling(0, mainController, playerController)
                whiling(1, mainController, playerController)
                whiling(2, mainController, playerController)
                whiling(3,mainController, playerController)
                whiling(4,mainController, playerController)
                whiling(5,mainController, playerController)
                delay(mainController.delay)
            }
        }

    }

    fun whiling(index: Int, mainController: MainController, playerController: PlayerController){
        if(enemyY[index]<mainController.screenHeight && enable[index]){
            Log.i("posEnemy", "${enemyX[0]} <= ${playerController.player}-${playerController.player+playerController.playerWidth} <= ${enemyX[0]+enemyWidth}")
            if(enemyList[index].up) enemyY[index]+=(mainController.moveSpeed/1.5).toInt()
            if(!enemyList[index].up) enemyY[index]+=mainController.moveSpeed
            if(
                (
                        (enemyX[index]+5<=playerController.player &&
                                playerController.player<=enemyX[index]+enemyWidth-5)
                        ||
                        (enemyX[index]+5<=playerController.player+playerController.playerWidth &&
                                playerController.player+playerController.playerWidth <= enemyX[index]+enemyWidth-5)
                        )
                &&
                (enemyY[index]>=mainController.screenHeight-playerController.playerHeight+15)
                &&
                (enemyY[index]<=mainController.screenHeight+20)
            ) {
                mainController.gameEnbl = false
                Log.i("dead", "${enemyY[index]} _____ ${mainController.screenHeight-playerController.playerHeight}")
            }

        } else{
            Log.i("index", index.toString())
            enable[index]=false
            if(!enable[index]) {
                enemyList[index] = enemyListSave[(0..7).random()]
                if(enemyList[index].up){
                    enemyX[index] = (((2..3).random()*mainController.screenWidth/4).toInt()+enemyWidth/3).toInt()
                } else enemyX[index] = (((0..1).random()*mainController.screenWidth/4).toInt()+enemyWidth/3).toInt()

                enemyY[index]= newY(enemyY)

                enable[index]=true
            }
        }
    }

    private fun newY(enemyYFun: MutableList<Int>): Int {
        val sort = enemyYFun.sorted().last()
        if(sort<=-enemyHight) {
            return (sort + ((-mainController.screenHeight * 2).toInt()..-(playerController.playerHeight + enemyHight).toInt()).random())
        }
        return ((-mainController.screenHeight * 3).toInt()..-(mainController.screenHeight+enemyHight).toInt()).random()
    }
}