package ru.porcupine.thebestgoalkeeper.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import ru.porcupine.thebestgoalkeeper.R
import ru.porcupine.thebestgoalkeeper.other.SharedPreference

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        val score = intent.getIntExtra("score", 0)
        var record = SharedPreference(this).getValueInt("record")

        if(score>record){
            record = score
            SharedPreference(this).save("record", record)
        }

        val playBtn = findViewById<Button>(R.id.play)
        val homeBtn = findViewById<Button>(R.id.menu)
        val scoreTxt = findViewById<TextView>(R.id.score)
        val recordTxt = findViewById<TextView>(R.id.record)

        playBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        homeBtn.setOnClickListener {
            startActivity(Intent(this, StartActivity::class.java))
            finish()
        }

        scoreTxt.text = "score\n\n$score"
        recordTxt.text = "record\n\n$record"
    }
}