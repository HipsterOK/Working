package ru.porcupine.savetheballoon

import android.content.Context

class ScreenSize(context:Context) {
    private val screenHeight = context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density
    val screenWidth = context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density
    val GOING_UP = -20f
    val FACING_DOWN = 45f
    val CENTER = 0f
    val OBSTACLE_WIDTH = screenWidth/5
    val MAX_OBSTACLE_HEIGHT = screenHeight/3
    val MIN_OBSTACLE_HEIGHT = screenHeight/6

}