package ru.porcupine.thebestgoalkeeper.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import ru.porcupine.thebestgoalkeeper.R
import ru.porcupine.thebestgoalkeeper.other.SharedPreference

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val playBtn = findViewById<Button>(R.id.play)
        val recordTxt = findViewById<TextView>(R.id.record)

        playBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        recordTxt.text = "record\n\n${SharedPreference(this).getValueInt("record")}"
    }
}