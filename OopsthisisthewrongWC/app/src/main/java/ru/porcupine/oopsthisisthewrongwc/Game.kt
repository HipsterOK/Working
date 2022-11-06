package ru.porcupine.oopsthisisthewrongwc

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Game(context:Context) {
    val font = R.font.steclo
    val screenHeight = context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val padding = screenWidth/35
    var time by mutableStateOf(30)
    var score by mutableStateOf(0)
    val heroSize  = screenWidth/3
    val sexList = listOf(R.drawable.man, R.drawable.woman)
    val posHeroList = listOf(-heroSize, screenWidth)
    val nullPosition = (screenWidth-heroSize)/2
    var sex by mutableStateOf(sexList[0])
    val WCSign = screenHeight/9
    var heroPos by mutableStateOf(nullPosition)
    val posWC = listOf((-screenWidth+WCSign)/2, (screenWidth-WCSign)/2)
    var manWCPos by mutableStateOf(posWC[0])
    var womanWCPos by mutableStateOf(posWC[1])
    val imgChooseList = listOf(Color.Green, Color.Red)
    var imgChoose by mutableStateOf(imgChooseList[0])
    var alphaChoose by mutableStateOf(0f)
    var wait = false
    var alphaHero by mutableStateOf(1f)

    fun newHero(){
        sex = sexList[(0..1).random()]
        womanWCPos = posWC[(0..1).random()]
        manWCPos = if(womanWCPos == posWC[0]) {
            posWC[1]
        } else posWC[0]
    }

    fun startTimer(context:Context, scope:CoroutineScope){
        scope.launch {
            while(time>0){
                delay(1000)
                time--
            }
            val resultActivity = Intent(context, ResultActivity::class.java)
            resultActivity.putExtra("score", score)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            resultActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(resultActivity)
        }
    }
}