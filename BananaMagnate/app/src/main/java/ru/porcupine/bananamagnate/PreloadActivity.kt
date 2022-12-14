package ru.porcupine.bananamagnate

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import ru.porcupine.bananamagnate.activities.MenuActivity

class PreloadActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long=3000 // 3 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preload)


        Handler().postDelayed({
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}