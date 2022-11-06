package ru.porcupine.taxidriver

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*

class RoadController(
    context: Context) {

    private val mainController = MainController(context)

    var y1: Float by mutableStateOf(0f)
    var y2: Float by mutableStateOf(-mainController.screenHeight)

    fun startRoad(mainController: MainController) {
        GlobalScope.launch {
            while (true) {
                while (mainController.gameEnbl) {
                    y1+=mainController.moveSpeed
                    y2+=mainController.moveSpeed
                    if(y1>=mainController.screenHeight){
                        y1=y2-mainController.screenHeight+mainController.moveSpeed
                    }
                    if(y2>=mainController.screenHeight){
                        y2=y1-mainController.screenHeight+mainController.moveSpeed
                    }
                    delay(mainController.delay)
                }
            }
        }
    }
}