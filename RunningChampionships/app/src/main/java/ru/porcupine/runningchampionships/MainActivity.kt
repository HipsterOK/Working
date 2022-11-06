package ru.porcupine.runningchampionships

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.porcupine.runningchampionships.models.GameState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val deviceMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(deviceMetrics)
            deviceWidthInPixels = deviceMetrics.widthPixels
            deviceHeightInPixels = deviceMetrics.heightPixels
            distanceBetweenEnemy = (deviceWidthInPixels * 0.8f).toInt()

            setContent {
                MainGameScene(GameState(this), this)
            }
        }
    }
}