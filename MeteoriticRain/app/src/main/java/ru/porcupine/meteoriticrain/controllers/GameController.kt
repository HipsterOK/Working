package ru.porcupine.meteoriticrain.controllers

import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.meteoriticrain.activities.MainActivity

var score = 0
var timer = 30

class GameController {
    val enemyController = EnemyController()

    fun start(mainActivity: MainActivity, timeTxt:TextView){
        var i = 0
        CoroutineScope(Dispatchers.IO).launch {
            while (timer>0){
                mainActivity.updateTime(timeTxt)
                delay(1000)
                timer--
                if(i==1){
                    kfSpeed++
                    i=0
                }
                i++
            }
            mainActivity.gameOver()
        }
    }
}