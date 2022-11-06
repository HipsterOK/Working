package ru.porcupine.cockroachtrap

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainController(context:Context) {
    var timer by mutableStateOf(20)
    var score by mutableStateOf(0)

    private val displayMetrics = context.resources.displayMetrics
    val screenHeight = displayMetrics.heightPixels / displayMetrics.density
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density

    var cockroachEnable = true

    val cockroachSize = screenWidth/5
    var cockroachX by mutableStateOf(0)
    private set
    var cockroachY by mutableStateOf(0)
    private set

    fun startTimer(context: MainActivity, delaySpawn: Long) {
        GlobalScope.launch {
            while (timer>0) {
                delay(1000)
                timer--
            }
            val resultIntent = Intent(context, ResultActivity::class.java)
            resultIntent.putExtra("score", score)
            resultIntent.putExtra("delay", delaySpawn)
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(resultIntent)
        }
    }

    fun spawnCockroach(delaySpawn: Long) {

        GlobalScope.launch {
            while (true) {
                cockroachX=(0..(screenWidth-cockroachSize).toInt()).random()
                cockroachY=(0..(screenHeight-cockroachSize).toInt()).random()
                cockroachEnable = true
//            cockroachX++
//            cockroachY=0
                delay(delaySpawn)
            }
        }
    }
}