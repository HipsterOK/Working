package ru.porcupine.followme

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameController(context: Context){

    var cardListRes by mutableStateOf(mutableListOf(
        R.drawable.btn_blue,
        R.drawable.btn_cyan,
        R.drawable.btn_green1,
        R.drawable.btn_orange,
        R.drawable.btn_pink,
        R.drawable.btn_red,
        R.drawable.btn_yellow,
        R.drawable.btn_blue2,
        R.drawable.btn_cyan2,
        R.drawable.btn_green2,
        R.drawable.btn_purpl,
        R.drawable.btn_red2,
        R.drawable.btn_yellow2,
        R.drawable.btn_blue3,
        R.drawable.btn_green3,
        R.drawable.btn_purple2
    )
    )

    var cardListVisible = mutableStateListOf<Float>()

    var count by mutableStateOf(0)
    private var displayMetrics = context.resources.displayMetrics
    var screenHeight = displayMetrics.heightPixels / displayMetrics.density
    var screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val btnSize = screenWidth/5
    var quireSize = 1
    var hp = 3
    var hpViewList = mutableListOf(
        R.drawable.hp_0,
        R.drawable.hp_1,
        R.drawable.hp_2,
        R.drawable.hp_3
    )
    var hpView by mutableStateOf(hpViewList[3])
    var quire = mutableListOf<Int>()
    var userPlay = false
    var playerSuccess = true
    var playerPos = 0
    var successIndicator by mutableStateOf(0f)
    var wrongIndicator by mutableStateOf(0f)
    var turn by mutableStateOf(R.drawable.bot)
    private var lastRandom = -1

    fun start(context: Context, scope: CoroutineScope){

        for(i in 0..15){
            cardListVisible.add(0.2f)
        }

        scope.launch{
                while(hp>0){
                    delay(1000)
                    if(hp>0) {
                        if (!userPlay) {
                            if (playerSuccess) {
                                newBot()
                            } else {
                                repeatBot()
                            }
                        }
                    }
                }
            val finalIntent = Intent(context, FinalActivity::class.java)
            finalIntent.putExtra("score", count)
            finalIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            finalIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            finalIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            finalIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(finalIntent)
        }
    }

    private suspend fun newBot(){
        delay(2000)
        for(i in 0 until quireSize) {
            if(i>0) {
                cardListVisible[quire[i - 1]] = 0.2f
            } else{
                var random = (0..15).random()
                while(random == lastRandom){
                    random = (0..15).random()
                }
                quire.add(random)
                lastRandom = random
            }
            cardListVisible[quire[i]] =1f
            delay(1000)
        }
        cardListVisible[quire[quireSize-1]]=0.2f
        userPlay=true
        turn = R.drawable.user
        Log.i("ss", quire.size.toString())
    }

    private suspend fun repeatBot(){
        delay(2000)
        for(i in 0 until quireSize) {
            if(i>0) {
                cardListVisible[quire[i - 1]] = 0.2f
            }
            cardListVisible[quire[i]] =1f
            delay(1000)
        }
        cardListVisible[quire[quireSize-1]]=0.2f
        userPlay=true
        turn = R.drawable.user
    }

}