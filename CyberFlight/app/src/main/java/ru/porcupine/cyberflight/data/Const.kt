package ru.porcupine.cyberflight.data

import android.content.Context
import ru.porcupine.cyberflight.R

class Const(context:Context) {
    val font = R.font.qore
    val screenHeight = context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val padding = screenHeight/40
    val heightPlane = screenHeight/10
    val widthPlane = screenWidth/5
    val playerPositionY = screenHeight-heightPlane-padding
    val moveSpeed = screenWidth/100
    val meteorSize = screenHeight/13
    val deadZone = screenWidth/60
}