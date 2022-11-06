package ru.porcupine.hitthegoal

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.timerTask

@OptIn(DelicateCoroutinesApi::class)
class MainActivityView: ViewModel() {
    var timer : Int by mutableStateOf(30)
        private set
    private var delayGates:Long =6
    private var delayGatesDouble=6.0

    private var positiveMove = true
    val configuration = Resources.getSystem().displayMetrics!!
    val screenWidth = configuration.widthPixels/configuration.density
    val gateWidth = screenWidth/4
    var positionGateX : Int by mutableStateOf(((screenWidth-gateWidth)/2).toInt())
        private set

    val gateHeight = gateWidth/1.5
    private var screenHeight = configuration.heightPixels/configuration.density
    val ballSize = screenWidth/8
    var butEnbl: Boolean by mutableStateOf(true)
        private set
    var positionBallY : Int by mutableStateOf(ballSize.toInt())
        private set
    var score:Int by mutableStateOf(0)
        private set
    var viewGoal:Float by mutableStateOf(0F)
        private set

    fun startTimer(context:Context){
        GlobalScope.launch(Dispatchers.Default) {
            while(timer > 0){
                timer--
                delayGatesDouble-=0.1
                delayGates= delayGatesDouble.toLong()
                delay(1000)
//                Log.i("timerView", timer.toString())
            }
            val resultActivity = Intent(context, ResultActivity::class.java)
            resultActivity.putExtra("result", score)
            context.startActivity(resultActivity)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        }
    }

    fun moveGates() {
        GlobalScope.launch(Dispatchers.IO) {
            while (true) {
                if (positionGateX >= (screenWidth - gateWidth).toInt() && positiveMove) {
                    positiveMove = false
                }
                if (positionGateX <= 0 && !positiveMove) {
                    positiveMove = true
                }
                if (positiveMove) {
                    positionGateX++
                } else positionGateX--
//                Log.i("postion", positionGateX.toString())
                delay(delayGates)
            }
        }
    }

    fun startBall(){
        butEnbl = false
        GlobalScope.launch(Dispatchers.IO) {
            while (positionBallY<screenHeight-ballSize-gateHeight/2) {
                positionBallY+=10
                delay(10)
            }
            if((screenWidth/2-gateWidth)<=positionGateX && (screenWidth/2)>=positionGateX){
                score++
                viewGoal=1F
                Timer().schedule(timerTask {
                    viewGoal=0F
                }, 1000)


//                Log.i("GateX", "${(screenWidth/2-gateWidth)} <= ${positionGateX} <= ${screenWidth/2}")
//                Log.i("Goal", "Goal ${score}")
            }
            positionBallY = ballSize.toInt()
            butEnbl = true
        }
    }
}