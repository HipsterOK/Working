package ru.porcupine.everyonetosleep

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.everyonetosleep.activities.ResultActivity
import java.util.*
import kotlin.concurrent.timerTask

class Logic(context: Context) {
    val screenHeight = context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    var windowImg = mutableStateListOf<Int>()
    var windowsLight = mutableListOf<Boolean>()
    var timer by mutableStateOf(30)
    var score by mutableStateOf(0)
    var count = 0
    val font = R.font.font

    fun fillWindow(){
        for(i in 0 until 78){
            windowImg.add(R.drawable.window_off)
            windowsLight.add(false)
        }
    }

    fun game(context:Context, scope:CoroutineScope){
        scope.launch {
            while(timer>0){
                timer--
                enableWindowId()
                Timer().schedule(timerTask {
                    enableWindowId()
                }, 500)
                delay(1000)
            }
            Timer().schedule(timerTask{
                val resultActivity = Intent(context, ResultActivity::class.java)
                resultActivity.putExtra("score", score)
                resultActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                resultActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(resultActivity)
            }, 1000)
        }
    }

    private fun enableWindowId(){
        var random = (0..77).random()
        while(windowsLight[random] && count<78){
            random = (0..77).random()
        }
        if(count<78) {
            count++
        }
        enableWindow(random)
    }

    private fun enableWindow(id:Int){
        windowImg[id] = R.drawable.window_light
        windowsLight[id] = true
    }
}