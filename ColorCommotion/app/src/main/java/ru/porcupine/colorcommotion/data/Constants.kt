package ru.porcupine.colorcommotion.data

import android.content.Context
import android.content.res.Configuration
import androidx.compose.ui.graphics.Color
import ru.porcupine.colorcommotion.R

class Constants(context: Context) {
    val font = R.font.dsgreece
    val screenHeight = if(context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
    context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density else
    context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density


    val screenWidth = if(context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
        context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density else
        context.resources.displayMetrics.heightPixels / context.resources.displayMetrics.density

    val padding = screenHeight / 45

    val textSize = screenHeight/35

    val arrayTextColor = listOf(
        Pair("Blue", Color.Blue),
        Pair("Green", Color.Green),
        Pair("Red", Color.Red),
        Pair("Yellow", Color.Yellow),
        Pair("Gray", Color.Gray),
        Pair("Cyan", Color.Cyan),
        )
}