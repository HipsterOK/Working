package ru.porcupine.onecircletwocircle.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.porcupine.onecircletwocircle.functions.SharedPreference
import ru.porcupine.onecircletwocircle.graphics.ResultUI
import ru.porcupine.onecircletwocircle.graphics.Template

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var record = SharedPreference(this).getValueInt("record")
        val score = intent.getIntExtra("score",0)
        if(score>record){
            record = score
            SharedPreference(this).save("record", record)
        }

        setContent {
            Template().Background()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ResultUI(this@ResultActivity).Play(this@ResultActivity)
                ResultUI(this@ResultActivity).Menu(this@ResultActivity)
                ResultUI(this@ResultActivity).Score(score = score)
                ResultUI(this@ResultActivity).BestRecord(record = record)
            }
        }
    }
}