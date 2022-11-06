package ru.porcupine.wherestheball

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.timerTask

class Controller(context: Context) {

    var menuBtn by mutableStateOf(false)
    var lose by mutableStateOf(0f)
    private val displayMetrics = context.resources.displayMetrics
    val screenHeight = displayMetrics.heightPixels / displayMetrics.density
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density

    val cupSize = screenWidth/4

    val sharedPreference = SharedPreference(context)

    var balance by mutableStateOf(sharedPreference.getValueInt("balance"))
    var bestBalance by mutableStateOf(sharedPreference.getValueInt("bestBalance"))
    var set by mutableStateOf(50)

    var cupX = mutableStateListOf(0, 0 ,0)
    var cupY = mutableStateListOf(0, 0 ,0)

    private var position = mutableListOf(0, 1, 2)

    var gameShuffle = false
    var choose = false

    fun startShuffle(scope: CoroutineScope){
        gameShuffle = true

        cupY[1] -= (screenHeight/10).toInt()



        Timer().schedule(timerTask {

            scope.launch {
                cupY[1] += (screenHeight/10).toInt()
                delay(1000)
                for(i in 0 until 8) {
                    val cup1 = (0..2).random()
                    var cup2 = (0..2).random()
                    while (cup1 == cup2) {
                        cup2 = (0..2).random()
                    }
                    val posCup1 = position.indexOf(cup1)
                    val posCup2 = position.indexOf(cup2)

                    position[posCup1] = cup2
                    position[posCup2] = cup1
                    Log.i("pos", "${position[0]} , ${position[1]}, ${position[2]}")

                    if (position.indexOf(cup1) < position.indexOf(cup2)) {
                        cupX[cup1] -= ((position.indexOf(cup2) - position.indexOf(cup1)) * (screenWidth / 4)).toInt()
                        cupX[cup2] += ((position.indexOf(cup2) - position.indexOf(cup1)) * (screenWidth / 4)).toInt()
//                    Log.i("pos1", "${position.indexOf(position.indexOf(cup2)-position.indexOf(cup1))}")
                    } else {
                        cupX[cup1] += ((position.indexOf(cup1) - position.indexOf(cup2)) * (screenWidth / 4)).toInt()
                        cupX[cup2] -= ((position.indexOf(cup1) - position.indexOf(cup2)) * (screenWidth / 4)).toInt()
//                    Log.i("pos2", "${position.indexOf(position.indexOf(cup1)-position.indexOf(cup2))}")
                    }
                    delay(1000)
                }
                choose = true
            }
        }, 1000)


    }
}