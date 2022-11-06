package ru.porcupine.gravityguy.activities

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import ru.porcupine.gravityguy.R
import ru.porcupine.gravityguy.utils.SharedPreference

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val playerImageMenu = findViewById<ImageView>(R.id.playerMenuImg).apply {
            setBackgroundResource(R.drawable.player_animation)
            playerAnimation = background as AnimationDrawable
        }
        playerAnimation.start()

        val playBtn = findViewById<Button>(R.id.playBtn)
        val bestScore = findViewById<TextView>(R.id.bestTxt)

        playBtn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        bestScore.text = "best score \n${SharedPreference(this).getValueInt("record")}"
    }
}