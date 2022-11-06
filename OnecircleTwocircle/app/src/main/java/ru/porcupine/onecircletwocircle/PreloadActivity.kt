package ru.porcupine.onecircletwocircle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.porcupine.onecircletwocircle.functions.ChangeActivity
import ru.porcupine.onecircletwocircle.graphics.PreloadUI
import java.util.*
import kotlin.concurrent.timerTask

class PreloadActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Timer().schedule(timerTask {
                ChangeActivity().newIntentHome(this@PreloadActivity)
            }, 3000)


            setContent {
                PreloadUI().Preload(context = this@PreloadActivity)
            }
        }
    }
}