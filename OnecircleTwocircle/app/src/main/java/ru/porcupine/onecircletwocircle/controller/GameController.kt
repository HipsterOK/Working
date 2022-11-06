package ru.porcupine.onecircletwocircle.controller

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.onecircletwocircle.R
import ru.porcupine.onecircletwocircle.functions.ChangeActivity
import java.util.*
import kotlin.concurrent.timerTask

class GameController(context: Context) {
    val font = R.font.karlo

    var delay = 50L

    val screenHeight = context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val padding = screenHeight/40
    val circlesSize = screenWidth/7
    val centerXPosition = (screenWidth-circlesSize)/2

    private var playerXOutPosition = listOf(padding, screenWidth-circlesSize-padding)
    private var playerXInPosition = listOf(centerXPosition-circlesSize/2, centerXPosition+circlesSize/2)
    var playerXPosition = mutableStateListOf(playerXInPosition[0], playerXInPosition[1])
    var playerPush = false
    var gameActive = true

    private var fallPos = mutableListOf(
        -circlesSize*1,
        -circlesSize*3,
        -circlesSize*5,
        -circlesSize*7,
        -circlesSize*9,
        -circlesSize*11
    )

    var fallYPosition = mutableStateListOf(
        0,0,0,0,0,0
    )


    var score by mutableStateOf(0)

    private val circleImage = listOf(R.drawable.black_circle, R.drawable.green_circle)

    var fallImg =listOf(
        circleImage[0],
        circleImage[0],
        circleImage[1],
        circleImage[1],
        circleImage[1],
        circleImage[0]
    )

    private var green =listOf(
        false,
        false,
        true,
        true,
        true,
        false
    )

    var alpha = mutableStateListOf(
       1f,1f,1f,1f,1f,1f
    )


    fun startFall(context:Context, scope: CoroutineScope){
        fallPos.shuffle()
        for(i in 0 until 6) {
            fallYPosition[i] = fallPos[i].toInt()
        }
        scope.launch {
            while(gameActive){
                Log.i("min", fallYPosition.min().toString())
                for(i in 0 until 6){
                    fallYPosition[i]+=5

                    if(
                        fallYPosition[i]>(screenHeight / 3 * 2) && fallYPosition[i]<(screenHeight / 3 * 2)+circlesSize
                    ) {
                        if (green[i]) {
                            if (!playerPush) {
                                score++
                                if(delay>20) {
                                    delay--
                                }
                                alpha[i] = 0f
                                respawn(i)
                                Timer().schedule(timerTask {
                                    alpha[i]=1f
                                }, delay*3)
                            }
                        } else {
                            if (!playerPush) {
                                gameActive = false
                                ChangeActivity().newIntentResult(context, score)
                            }
                        }
                    }

                    if(
                        fallYPosition[i]>=screenHeight - circlesSize - padding
                    ) {
                        if (!green[i]) {
                                score++
                            if(delay>20) {
                                delay--
                            }
                                alpha[i] = 0f
                           respawn(i)

                                Timer().schedule(timerTask {
                                    alpha[i]=1f
                                }, delay*3)
                            } else {
                                gameActive = false
                                ChangeActivity().newIntentResult(context, score)
                            }
                    }
                }
                delay(delay)
            }
        }
    }


    fun push(){
        playerPush = true
        playerXPosition[0] = playerXOutPosition[0]
        playerXPosition[1] = playerXOutPosition[1]
    }

    fun unPush(){
        playerPush = false
        playerXPosition[0] = playerXInPosition[0]
        playerXPosition[1] = playerXInPosition[1]
    }

    private fun respawn(i:Int){
        if(fallYPosition.min()<(-circlesSize)){
            fallYPosition[i] = (fallYPosition.min()-circlesSize*2).toInt()
        } else fallYPosition[i] = (-circlesSize*2).toInt()
    }
}