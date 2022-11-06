package ru.porcupine.everyonetosleep.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.porcupine.everyonetosleep.Elements
import ru.porcupine.everyonetosleep.Logic
import ru.porcupine.everyonetosleep.R
import ru.porcupine.everyonetosleep.SharedPreference

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gameLogic = Logic(this)

        setContent {
            val record = SharedPreference(this).getValueInt("record")
            Elements().Background(R.drawable.background)
            Box(Modifier.fillMaxSize()){
                Elements().ButtonView(gameLogic = gameLogic, img = R.drawable.policy, h = 16, w = 7) {
                    val url = getString(R.string.url)
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(this@MenuActivity, Uri.parse(url))
                }

                Column(
                    Modifier.align(Alignment.Center)
                        .padding(bottom = (gameLogic.screenHeight/4).dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    ) {
                    Box(Modifier.padding((gameLogic.screenWidth/15).dp)) {
                        Elements().TextView(
                            gameLogic = gameLogic,
                            text = "Best: $record",
                            h = 15,
                            w = 3.8
                        )
                    }
                    Row {
                        Box(Modifier.padding((gameLogic.screenWidth/20).dp)){
                        Elements().ButtonView(gameLogic, R.drawable.play, 12, 5, fun() {
                            val gameIntent = Intent(this@MenuActivity, MainActivity::class.java)
                            startActivity(gameIntent)
                            finish()
                        })
                    }
                    Box(Modifier.padding((gameLogic.screenWidth/20).dp)) {
                        Elements().ButtonView(gameLogic, R.drawable.info, 12, 5, fun() {
                            val gameIntent = Intent(this@MenuActivity, InfoActivity::class.java)
                            startActivity(gameIntent)
                        })
                    }
                    }
                }
              Image(
                  painter = painterResource(id = R.drawable.hero),
                  contentDescription = "",
                  modifier = Modifier
                      .align(Alignment.BottomStart)
                      .size((gameLogic.screenHeight / 2).dp),
              )
            }
        }
    }
}