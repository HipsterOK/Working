package ru.porcupine.buildapedestal.other

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.buildapedestal.R
import ru.porcupine.buildapedestal.activities.ResultActivity
import java.util.*
import kotlin.concurrent.timerTask

class GameController(context: Context) {
    val screenHeight = context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val font = R.font.egipet_bold
    var score by mutableStateOf(0)
    var gameEnable = true
    var directionRight = true
    var moving = true
    var tempCount = 0
    var hp = 3
    var hpView = mutableStateListOf(
        1f,
        1f,
        1f
    )

    var speed = screenWidth/150

    var bottomImg by mutableStateOf(R.drawable.pedestal_bottom)

    var freePedestal = mutableListOf(true, true, true, true)

    val scoreHeight = screenHeight/10
    val pedestalSize = screenWidth/4
    val padding = screenWidth/20

    var countHeight = 1
    var currentIndex = 0

    var pedestalAngle = mutableStateListOf(
        0,
        0,
        0,
        0
    )

    var pedestalTopPosX by mutableStateOf(((screenWidth-pedestalSize)/2))
    var pedestalTopPosY by mutableStateOf(-pedestalSize*3)

    var pedestalMiddlePosX = mutableStateListOf(
        (screenWidth-pedestalSize)/2,
        (screenWidth-pedestalSize)/2,
        (screenWidth-pedestalSize)/2,
        (screenWidth-pedestalSize)/2
    )
    var pedestalMiddlePosY = mutableStateListOf(
        0,
        -pedestalSize.toInt(),
        -pedestalSize.toInt(),
        -pedestalSize.toInt()
    )

    var pedestalBottomPosX by mutableStateOf(((screenWidth-pedestalSize)/2))
    var pedestalBottomPosY by mutableStateOf((screenHeight-(pedestalSize+scoreHeight+padding)))

    fun movePedestal(scope: CoroutineScope){
        scope.launch {
            while(gameEnable) {
                if (moving) {
                    if (directionRight && pedestalMiddlePosX[currentIndex] <= (screenWidth - pedestalSize)) {
                        pedestalMiddlePosX[currentIndex] = pedestalMiddlePosX[currentIndex]+speed
                    } else directionRight = false

                    if (!directionRight && pedestalMiddlePosX[currentIndex] >= 0) {
                        pedestalMiddlePosX[currentIndex] = pedestalMiddlePosX[currentIndex]-speed
                    } else directionRight = true
                }
                delay(12)
            }
        }
    }

    fun upping(){
        pedestalBottomPosY = screenHeight+pedestalSize
        countHeight=1
        tempCount=0
        currentIndex=0
        for(i in 0 until 4){
            pedestalMiddlePosY[i] += screenHeight.toInt()
        }
        Timer().schedule(timerTask{
            for(i in 0 until 4){
                pedestalMiddlePosX[i] = screenWidth
            }
        }, 100)

        Timer().schedule(timerTask{
            for(i in 0 until 4){
                pedestalMiddlePosY[i] = (-pedestalSize).toInt()
            }
        }, 200)

        Timer().schedule(timerTask{
            bottomImg = R.drawable.pedestal_center
            for(i in 0 until 4){
                freePedestal[i] = true
                pedestalBottomPosY = screenHeight-(pedestalSize+scoreHeight+padding)
            }
        }, 300)

        Timer().schedule(timerTask{
            freePedestal[0] = true
            pedestalMiddlePosY[0]= 0
        }, 400)
    }

    fun nextIndex(){
        if (currentIndex < 3) {
           currentIndex++
        } else currentIndex = 0
    }

    fun gameOver(context:Context){
        upping()
        Timer().schedule(timerTask{
            pedestalTopPosY = (screenHeight-((pedestalSize*3)+scoreHeight+padding))
        }, 500)

        Timer().schedule(timerTask{
            val resultActivity = Intent(context, ResultActivity::class.java)
            resultActivity.putExtra("score", score)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(resultActivity)
        }, 1500)
    }
}