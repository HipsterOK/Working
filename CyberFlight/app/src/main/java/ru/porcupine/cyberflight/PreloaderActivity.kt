package ru.porcupine.cyberflight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.porcupine.cyberflight.controllers.ActivityController
import ru.porcupine.cyberflight.ui.elements.PreloaderUI
import java.util.*
import kotlin.concurrent.timerTask

class PreloaderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Timer().schedule(timerTask {
                ActivityController().activityHome(this@PreloaderActivity)
            }, 3000)


            setContent {
                PreloaderUI().Preloader(context = this@PreloaderActivity)
            }
        }
    }
}