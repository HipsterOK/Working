package ru.porcupine.pizzadeliveryguy

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RoadController(context: Context) {
    private val mainControl = MainController(context)

    var pad1: Int by mutableStateOf(0)
    var pad2: Int by mutableStateOf(mainControl.screenHeight.toInt()-16)
    var pad2Top: Int by mutableStateOf(0)
    var pad1Top: Int by mutableStateOf(0)

    @OptIn(DelicateCoroutinesApi::class)
    fun startCoroutine(mainControl: MainController){
        GlobalScope.launch {
            while (mainControl.gameEnbl) {
                if(pad1<=mainControl.moveSpeed){
                    pad2-=mainControl.moveSpeed
                    pad1Top+=mainControl.moveSpeed
                    if(pad2<=mainControl.moveSpeed) {
                        pad1Top = 0
                        pad1 = mainControl.screenHeight.toInt()-16
                    }
                }
                if(pad2<=mainControl.moveSpeed){
                    pad1-=mainControl.moveSpeed
                    pad2Top+=mainControl.moveSpeed
                    if(pad1<=mainControl.moveSpeed) {
                        pad2Top = 0
                        pad2 = mainControl.screenHeight.toInt()-16
                    }
                }
                delay(mainControl.delay)
            }
        }
    }
}