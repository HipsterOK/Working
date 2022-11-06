package ru.porcupine.pizzadeliveryguy

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CarsController(context: Context) {
    var carList = mutableListOf<CarsModel>()
    private val mainControl = MainController(context)
    val carWidth = mainControl.screenWidth/6
    val carHight = carWidth*2
    var carYPadding = mutableStateListOf(
        (mainControl.screenHeight.toInt()..(mainControl.screenHeight*3).toInt()).random(),
        (mainControl.screenHeight.toInt()..(mainControl.screenHeight*3).toInt()).random(),
        (mainControl.screenHeight.toInt()..(mainControl.screenHeight*3).toInt()).random(),
        (mainControl.screenHeight.toInt()..(mainControl.screenHeight*3).toInt()).random()
    )
        private set

    var carYPaddingTop = mutableStateListOf(0,0,0,0)
    private set

    var paddingXList = mutableStateListOf(
        0,0,0,0
    )
        private set
    var enable = mutableListOf(true, true, true, true, true)


    private fun createCars(){
       carList = CarAdapter().fillCars()
    }

    private fun setPadding(){
        for(i in 0..3){
            paddingXList[i]= (0..(mainControl.screenWidth-carWidth).toInt()).random()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun spawn(mainControl: MainController){
        setPadding()
        createCars()
        GlobalScope.launch {
            while (mainControl.gameEnbl) {
                whiling(0, mainControl)
                Log.i("Y", "${mainControl.screenHeight-mainControl.playerHeight}<${carYPaddingTop[0]}<${mainControl.screenHeight}")
                whiling(1, mainControl)
                whiling(2, mainControl)
                whiling(3,mainControl)
                delay(mainControl.delay)
            }
        }

    }

    fun whiling(index: Int, mainControl: MainController){
        if(carYPaddingTop[index]<mainControl.screenHeight && enable[index]){
            if(carYPadding[index]>mainControl.moveSpeed){
                carYPadding[index]-=mainControl.moveSpeed
            }
            if(carYPadding[index]<=mainControl.screenHeight-carHight) {
                carYPaddingTop[index]+=mainControl.moveSpeed
            }
            if(((paddingXList[index]<=mainControl.playerX &&
                        mainControl.playerX<=paddingXList[index]+carWidth)||
                (paddingXList[index]<=mainControl.playerX+mainControl.playerWidth &&
                        mainControl.playerX+mainControl.playerWidth <= carWidth+paddingXList[index])) &&
                (carYPadding[index]<=mainControl.playerHeight && carYPaddingTop[index]<=mainControl.screenHeight-50)) {
                mainControl.gameEnbl = false
                Log.i("position X","Position X")
            }

        } else{
            enable[index]=false
            if(!enable[index]) {
                carList[index] = carList[(0..9).random()]
                carYPadding[index] =
                    (mainControl.screenHeight.toInt()..(mainControl.screenHeight * 3).toInt()).random()
                carYPaddingTop[index]=0
                paddingXList[index] = (0..(mainControl.screenWidth - carWidth).toInt()).random()
                enable[index]=true
            }
        }
    }
}