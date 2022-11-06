package ru.porcupine.monstercatcher.data

import android.content.Context
import android.content.res.Configuration
import ru.porcupine.monstercatcher.R

class Constants(context: Context) {
    val font = R.font.cartoon
    val screenHeight =
        if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density else
            context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density


    val screenWidth =
        if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density else
            context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density

    val padding = screenHeight / 45

    val textSize = screenHeight / 25

    val playerSize = screenWidth / 5

    val enemySize = screenWidth/5

    val bulletSize = screenWidth/8

    val speed = screenHeight/150
}