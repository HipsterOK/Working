package ru.porcupine.economyquiz.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.porcupine.economyquiz.elements.Elements
import ru.porcupine.economyquiz.elements.MenuElements
import ru.porcupine.economyquiz.functions.SharedPreference

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val record = SharedPreference(this).getValueInt("record")

        setContent {
            Elements().Background()
            MenuElements(this@MenuActivity).Policy(this)
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
                    MenuElements(this@MenuActivity).PlayButton(this@MenuActivity)
                    MenuElements(this@MenuActivity).InfoButton(this@MenuActivity)
                }
                MenuElements(this@MenuActivity).BestRecord(record = record)
                Box() {
                    MenuElements(this@MenuActivity).Character()
                    MenuElements(this@MenuActivity).Money()
                }
            }
        }
    }
}