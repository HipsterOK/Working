package ru.porcupine.rabbithole.controllers

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.rabbithole.R
import ru.porcupine.rabbithole.data.Constants
import ru.porcupine.rabbithole.data.Variables
import ru.porcupine.rabbithole.utils.SharedPreference
import java.util.*
import kotlin.concurrent.timerTask

class GameController(context: Context) {
    val constants = Constants(context)
    val variables = Variables(context)
    val screenController = ScreenController(this)

    fun initGamePos(){
        initGamePosLine(5)
        initGamePosLine(4)
        initGamePosLine(3)
        initGamePosLine(2)
        initGamePosLine(1)
        initGamePosLine(0)
    }

    private fun initGamePosLine(y:Int){
        val x1:Int = (0..3).random()
        var x2 = (0..3).random()
        while(x1 == x2){
            x2 = (0..3).random()
        }

        variables.checkGamePos[y][x1]=1
        variables.checkGamePos[y][x2]=1
    }

    fun openPosGame(){
        openLine(variables.turn)
    }

    private fun openLine(y:Int){
        for(i in 0 until 4) {
            if(variables.checkGamePos[y][i] == 1) {
                variables.imageGamePos[y][i] = R.drawable.true_position
            }
        }
    }

    fun newGame(){
        for(i in 0 until 3) {
            variables.alphaHeart[i] = 1f
        }
        variables.hp=3
        variables.score=10
        variables.scoreView=10
        variables.turn = 5
        for(i in 0 until 6){
            for (j in 0 until 4) {
                variables.checkGamePos[i][j]=0
                variables.imageGamePos[i][j]=R.drawable.false_position
            }
        }
    }

    fun checkTurn(){
        if(variables.turn==0) {
            Timer().schedule(timerTask {
                variables.turn = 5
                for(i in 0 until 6){
                        for (j in 0 until 4) {
                            variables.checkGamePos[i][j]=0
                            variables.imageGamePos[i][j]=R.drawable.false_position
                        }
                }
                initGamePos()
            }, 2000)
        }
    }

    private fun hpPlus(){
        if(variables.hp<3){
            variables.hp++
            variables.alphaHeart[variables.hp-1] = 1f
        }
    }

    private fun hpMinus(gameController: GameController){
        if(variables.hp>1) {
            variables.alphaHeart[variables.hp-1] = 0f
            variables.hp--
        }
        else{
            Timer().schedule(timerTask {
                screenController.gameOver(gameController)
            }, 500)
        }
    }

    fun checkWin(x:Int, y:Int, gameController: GameController){
        if(variables.checkGamePos[y][x]==1){
            variables.score= (variables.score*1.5).toInt()
            hpPlus()
        } else {
            hpMinus(gameController)
        }
    }

    fun updateCounter(){
        CoroutineScope(Dispatchers.IO).launch {
            while (variables.scoreView<variables.score){
                variables.scoreView++
                delay(100)
            }
        }
    }

    fun updateRecord(context: Context){
        if(variables.score>variables.record){
            variables.record = variables.score
            SharedPreference(context).save("record", variables.record)
        }
    }
}