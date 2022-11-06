package ru.porcupine.taxidriver

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlayerController(
    context: Context
) {
    private val mainController = MainController(context)

    var player by mutableStateOf(mainController.screenWidth/2)
    var move = 0f

    val playerWidth = mainController.screenWidth/7
    val playerHeight = playerWidth*2

    fun startControl(mainController: MainController) {
        GlobalScope.launch {
            while (mainController.gameEnbl) {
                Log.i("ss", player.toString())
                delay(1)
                player+=move
                if(move>0 && player>=mainController.screenWidth-playerWidth){
                    player=mainController.screenWidth-playerWidth
                }
                if(move<0 && player<=0){
                    player=0f
                }
            }
        }
    }
}