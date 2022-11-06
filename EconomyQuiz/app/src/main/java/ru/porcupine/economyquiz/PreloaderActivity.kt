package ru.porcupine.economyquiz

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.porcupine.economyquiz.activities.MenuActivity
import ru.porcupine.economyquiz.elements.PreloaderElements
import java.util.*
import kotlin.concurrent.timerTask

class PreloaderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timer().schedule(timerTask {
            val menuIntent = Intent(this@PreloaderActivity, MenuActivity::class.java)
            startActivity(menuIntent)
            finish()
        }, 3000)


        setContent {
            PreloaderElements().GifPreloader(context = this@PreloaderActivity)
        }
    }
}