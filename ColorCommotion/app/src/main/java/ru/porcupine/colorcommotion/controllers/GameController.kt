package ru.porcupine.colorcommotion.controllers

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.colorcommotion.data.Constants
import ru.porcupine.colorcommotion.data.Variables
import ru.porcupine.colorcommotion.utils.SharedPreference

class GameController(val context: Context) {
    val constants = Constants(context)
    val variables = Variables(context)
    val screenController = ScreenController(constants)

    fun initVar(){
        variables.score=0
        variables.gameEnable = true
        variables.turnId = 0
        variables.time=30
        variables.arrayTextColor.clear()
        variables.arrayColor.clear()
        variables.arrayBackgroundColor.clear()
        variables.arrayButtonColor.clear()
        constants.arrayTextColor.forEach {
            variables.arrayTextColor.add(it.first)
            variables.arrayColor.add(it.second)
            variables.arrayBackgroundColor.add(it.second)
            variables.arrayButtonColor.add(it.second)
        }
        randomColorText()
    }

    fun startGame(){
        CoroutineScope(Dispatchers.IO).launch {
            while (variables.time>0){
                delay(1000)
                variables.time--
            }
            screenController.goResult(this@GameController)
        }
    }

    private fun randomColorText(){
        variables.arrayTextColor.shuffle()
        variables.arrayColor.shuffle()
        variables.arrayBackgroundColor.clear()
        for(i in 5 downTo 0){
            variables.arrayBackgroundColor.add(variables.arrayColor[i])
        }
        variables.arrayButtonColor.shuffle()
    }

    fun checkTap(id:Int){
        val colorTurn = variables.arrayTextColor[variables.turnId]
        val colorTap = variables.arrayButtonColor[id]
        var find = false
        for (i in 0 until constants.arrayTextColor.size) {
            if(constants.arrayTextColor[i].first == colorTurn && constants.arrayTextColor[i].second == colorTap){
                find=true
                break
            }
        }
        if(find) {
            variables.score++
            variables.turnId++
        } else {
            variables.turnId=0
            randomColorText()
        }
        checkTurnId()
    }

    private fun checkTurnId(){
        if(variables.turnId==6){
            variables.turnId=0
            randomColorText()
        }
    }

    fun checkRecord(context: Context){
        if(variables.score>variables.record){
            variables.record= variables.score
            SharedPreference(context).save("record", variables.record)
        }
    }
}