package ru.porcupine.whostolemycheese.models

import androidx.compose.runtime.MutableState
import ru.porcupine.whostolemycheese.R

class PlayerModel(
    var image:MutableState<Int>,
    var saveState:Boolean,
) {
    fun changeStateToUnsave(){
        image.value = R.drawable.mouse_cheese
        saveState = false
    }

    fun changeStateToSave(){
        image.value = R.drawable.mouse
        saveState = true
    }
}