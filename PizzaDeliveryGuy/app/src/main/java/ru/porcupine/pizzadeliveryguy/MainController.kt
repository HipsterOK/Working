package ru.porcupine.pizzadeliveryguy

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*

class MainController(context:Context) {
    private var displayMetrics = context.resources.displayMetrics
    var screenHeight = displayMetrics.heightPixels / displayMetrics.density
    var screenWidth = displayMetrics.widthPixels / displayMetrics.density
    var playerWidth = screenWidth/8
    val playerHeight = playerWidth*2
    var timer: Int by mutableStateOf(0)
        private set
    var playerX: Int by mutableStateOf(((screenWidth-playerWidth)/2).toInt())
    var gameEnbl = true
    var delay:Long=4
    var moveSpeed=2
    var floatDelay = 4.0
    var floatMoveSpeed=2.0
    var context=context

    @OptIn(DelicateCoroutinesApi::class)
    fun startTimer(){
        delay=4
        moveSpeed=2
        floatDelay = 4.0
        floatMoveSpeed=2.0
        timer = 0
        GlobalScope.launch(Dispatchers.Main) {
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
            val resultActivity = Intent(context, ResultActivity::class.java)
            resultActivity.putExtra("score", timer)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(resultActivity)

        }
    }
}