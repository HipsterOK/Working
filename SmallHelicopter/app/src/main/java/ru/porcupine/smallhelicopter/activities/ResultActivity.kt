package ru.porcupine.smallhelicopter.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import ru.porcupine.smallhelicopter.R
import ru.porcupine.smallhelicopter.utils.SharedPreference

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var record = SharedPreference(this).getValueInt("record")
        val score = intent.getIntExtra("score", 0)

        if(score>record){
            record= score
            SharedPreference(this).save("record", record)
        }

        val playBtn = findViewById<Button>(R.id.playBtn)
        val menuBtn = findViewById<Button>(R.id.menuBtn)
        val recordTxt = findViewById<TextView>(R.id.bestTxt)
        val scoreTxt = findViewById<TextView>(R.id.scoreTxt)

        playBtn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        menuBtn.setOnClickListener{
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }

        recordTxt.text = "best score\n${record}"
        scoreTxt.text = "score\n${score}"
    }
}