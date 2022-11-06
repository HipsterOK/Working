package ru.porcupine.oopsthisisthewrongwc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game = Game(this)
        game.startTimer(this, lifecycleScope)

        setContent {
            GameElements().Background()
            GameElements().Hero(game)
            GameElements().WCMan(game)
            GameElements().WCWoman(game)
            GameElements().Timer(game)
            GameElements().Choose(game)
            GameElements().Controller(game)
        }
    }
}