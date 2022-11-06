package ru.porcupine.economyquiz.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.porcupine.economyquiz.elements.Elements
import ru.porcupine.economyquiz.elements.MenuElements
import ru.porcupine.economyquiz.elements.ResultElements
import ru.porcupine.economyquiz.functions.SharedPreference
import ru.porcupine.economyquiz.ui.theme.EconomyQuizTheme

class ResultsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var record = SharedPreference(this).getValueInt("record")
        val score = intent.getIntExtra("score",0)
        if(score>record){
            record = score
            SharedPreference(this).save("record", record)
        }

        setContent {
            Elements().Background()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    ResultElements(this@ResultsActivity).PlayButton(this@ResultsActivity)
                    ResultElements(this@ResultsActivity).HomeButton(this@ResultsActivity)
                }
                ResultElements(this@ResultsActivity).Score(score = score)
                ResultElements(this@ResultsActivity).BestRecord(record = record)
                ResultElements(this@ResultsActivity).Character()
            }
        }
    }
}