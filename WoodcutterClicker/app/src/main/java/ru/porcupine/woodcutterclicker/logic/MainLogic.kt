package ru.porcupine.woodcutterclicker.logic

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.woodcutterclicker.R
import ru.porcupine.woodcutterclicker.activities.WinActivity
import ru.porcupine.woodcutterclicker.tools.SharedPreference
import java.util.*
import kotlin.concurrent.timerTask

class MainLogic(context: Context) {
    val font = R.font.woodlook
    val screenHeight = context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val padding = screenHeight/40
    var axeHeight by mutableStateOf(screenHeight/3)
    var axeWidth by mutableStateOf(screenWidth/2)
    val woodSize = screenWidth/6

    var oneTap = SharedPreference(context).getValueInt("oneTap")
    var oneSecond = SharedPreference(context).getValueInt("oneSecond")

    var balance by mutableStateOf(SharedPreference(context).getValueInt("balance"))
    var gameEnable = true

    fun tapAnimation(){
        axeHeight += padding
        axeWidth += padding
        Timer().schedule(timerTask {
            axeHeight -= padding
            axeWidth -= padding
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