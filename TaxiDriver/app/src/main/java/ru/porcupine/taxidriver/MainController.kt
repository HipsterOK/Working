package ru.porcupine.taxidriver

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainController(private val context: Context) {
    var delay:Long=4
    var moveSpeed=2
    private var floatDelay = 4.0
    private var floatMoveSpeed=2.0
    var timer by mutableStateOf(0)
    private val displayMetrics = context.resources.displayMetrics
    var screenHeight = displayMetrics.heightPixels / displayMetrics.density
    var screenWidth = displayMetrics.widthPixels / displayMetrics.density
    var gameEnbl=true

    fun startTimer(){
        timer = 0
        GlobalScope.launch {
            while(gameEnbl){
                delay(1000)
                timer++
                if(floatDelay>3) {
                    floatDelay -= 0.1
                    delay=floatDelay.toLong()
                }
                if(floatMoveSpeed<3) {
                    floatMoveSpeed += 0.1
                    moveSpeed = floatMoveSpeed.toInt()
                }
                Log.i("delay", delay.toString())
                Log.i("speed", moveSpeed.toString())
            }
            val endActivity = Intent(context, EndActivity::class.java)
            endActivity.putExtra("time", timer)
            endActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            endActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            endActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            endActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(endActivity)
        }
    }
}