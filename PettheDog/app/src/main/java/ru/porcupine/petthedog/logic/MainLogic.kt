package ru.porcupine.petthedog.logic

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.petthedog.R
import ru.porcupine.petthedog.activities.WinActivity
import ru.porcupine.petthedog.tools.SharedPreference
import java.util.*
import kotlin.concurrent.timerTask

class MainLogic(context: Context) {
    val font = R.font.dog
    val screenHeight = context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val padding = screenHeight/40
    var dogHeight by mutableStateOf(screenHeight/3)
    var dogWidth by mutableStateOf(screenWidth/2)
    val heartSize = screenWidth/6

    var oneTap = SharedPreference(context).getValueInt("oneTap")
    var oneSecond = SharedPreference(context).getValueInt("oneSecond")

    var balance by mutableStateOf(SharedPreference(context).getValueInt("balance"))
    var gameEnable = true

    fun tapAnimation(){
        dogHeight += padding
        dogWidth += padding
        Timer().schedule(timerTask {
            dogHeight -= padding
            dogWidth -= padding
        }, 100)
    }

    fun startThread(scope:CoroutineScope, context:Context){
        scope.launch {
            while(gameEnable){
                if(oneSecond>0) {
                    balance += oneSecond
                    tapAnimation()
                }
                delay(1000)
            }
                val winActivity = Intent(context, WinActivity::class.java)
                winActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                winActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                winActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                winActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(winActivity)
        }
    }

}