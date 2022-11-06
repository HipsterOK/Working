package ru.porcupine.petthedog.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.porcupine.petthedog.logic.MainLogic
import ru.porcupine.petthedog.tools.SharedPreference
import ru.porcupine.petthedog.ui.theme.Patterns

class WinActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainLogic = MainLogic(this)

        setContent {
            Patterns().Background()
            Column(Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Win(mainLogic)
                Home(context = this@WinActivity, mainLogic = mainLogic)
            }
        }
    }

    @Composable
    fun Win(mainLogic: MainLogic){
        Patterns().TemplateText(
            mainLogic = mainLogic,
            text = "YOU\nWIN!", h = 8f, w = 1.5f, font = 10f)
    }

    @Composable
    fun Home(context: Context, mainLogic: MainLogic) {
        Patterns().Button(
            mainLogic = mainLogic,
            text = "Menu",
            h = 14f,
            w = 3f,
            font = 20f
        ) {
            SharedPreference(this).save("balance", 0)
            SharedPreference(this).save("oneTap", 1)
            SharedPreference(this).save("oneSecond", 0)
            SharedPreference(this).save("price0", 10)
            SharedPreference(this).save("price1", 10)
            SharedPreference(this).save("price2", 100)
            SharedPreference(this).save("price3", 200)
            SharedPreference(this).save("price4", 1000000)
            val menuActivity = Intent(context, MenuActivity::class.java)
            startActivity(menuActivity)
            finish()
        }
    }
}