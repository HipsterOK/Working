package ru.porcupine.cyberflight.data

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Player(context: Context) {
    var positionX by mutableStateOf(((Const(context).screenWidth-Const(context).widthPlane)/2))
}