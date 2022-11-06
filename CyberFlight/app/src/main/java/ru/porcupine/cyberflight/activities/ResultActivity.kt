package ru.porcupine.cyberflight.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.porcupine.cyberflight.functions.SharedPreference
import ru.porcupine.cyberflight.ui.elements.Default
import ru.porcupine.cyberflight.ui.elements.ResultUI

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
            Default().Background(this@ResultActivity)
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ResultUI(this@ResultActivity).Play(this@ResultActivity)
                ResultUI(this@ResultActivity).Menu(this@ResultActivity)
                ResultUI(this@ResultActivity).Score(score = score)
                ResultUI(this@ResultActivity).Record(record = record)
            }
        }
    }
}