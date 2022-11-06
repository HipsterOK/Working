package ru.porcupine.dodgethelightning

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.dodgethelightning.activities.FinalActivity

class Controller(context: Context) {

    val screenHeight = context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val font = R.font.futura_round
    val playerSize = screenWidth/5
    val cloudSize = screenWidth/2

    var score by mutableStateOf(0)

    var dogX by mutableStateOf(0)
    val posXTree = (screenWidth/2).toInt()
    private var delay = 1200L

    var cloudState by mutableStateOf(R.drawable.clear_cloud)

    var alphaFlash by mutableStateOf(0f)
    private var gameEnable = true

    fun startGame(scope:CoroutineScope, context:Context){
        var random = (0..5).random()
        scope.launch {
            while (gameEnable){
                if(dogX==posXTree){
                    score++
                }
                if(dogX==0 && score>0){
                    score--
                }
                if(random == 5){
                    cloudState = R.drawable.medium_cloud
                   delay(delay)
                    cloudState = R.drawable.before_thunder
                    delay(delay)
                    cloudState = R.drawable.thunder
                    alphaFlash = 0.8f
                    if(dogX==posXTree){
                        gameEnable = false
                    }
                   delay(delay/2)
                    alphaFlash = 0f
                    delay(delay)
                    cloudState = R.drawable.clear_cloud
                    if(delay>400){
                        delay-=100
                    }
                }

                random = (0..5).random()
                delay(1000)
            }

            val resultActivity = Intent(context, FinalActivity::class.java)
            resultActivity.putExtra("score", score)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(resultActivity)
        }
    }

}