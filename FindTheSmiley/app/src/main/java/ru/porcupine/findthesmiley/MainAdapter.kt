package ru.porcupine.findthesmiley

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainAdapter(context: Context) {

    var timer by mutableStateOf(30)
    var count by mutableStateOf(0)
    private var displayMetrics = context.resources.displayMetrics
    var screenHeight = displayMetrics.heightPixels / displayMetrics.density
    var screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val smileySize = screenWidth/6
    var smileyList = mutableStateOf(mutableListOf<SmileyModel>())
    var findSmiley by mutableStateOf(SmileyModel(0,R.drawable.smile_1))

    fun startTimer(scope: CoroutineScope,context:Context) {
        smileyList.value = SmileyAdapter().fillSmiley()
        smileyList.value.shuffle()

        findSmiley = smileyList.value[(0..39).random()]

        scope.launch {
            while (timer>0){
                delay(1000)
                timer--
            }
            val resultIntent = Intent(context, ResultActivity::class.java)
            resultIntent.putExtra("score", count)
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(resultIntent)
        }
    }

    fun shuffleSmiley(){
        smileyList.value = SmileyAdapter().fillSmiley()
        smileyList.value.shuffle()
        findSmiley = smileyList.value[(0..39).random()]
    }
}