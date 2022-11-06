package ru.porcupine.thebestgoalkeeper.controllers

import android.util.Log
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.thebestgoalkeeper.activities.MainActivity
import ru.porcupine.thebestgoalkeeper.activities.constants
import ru.porcupine.thebestgoalkeeper.models.EnemyModel

var kfSpeed = 1

class EnemyController {
    val enemyModels = mutableListOf(
        EnemyModel(3),
        EnemyModel(4),
        EnemyModel(5),
        EnemyModel(6),
        EnemyModel(7),
        EnemyModel(8),
        EnemyModel(9),
    )

    fun move(enemyImages:MutableList<ImageView>, mainActivity: MainActivity){
        CoroutineScope(Dispatchers.IO).launch {
            while (timer >0){
                delay(20)
                enemyModels.forEach {
                    it.y+= it.speed+ kfSpeed
                    if(it.y > constants.screenHeight){
                        it.spawn()
                    }
                }
                Log.i("fall", enemyModels[0].y.toString())
                Log.i("fall", enemyModels[0].x.toString())
                mainActivity.updateEnemyPos(enemyImages)
            }
        }
    }
}