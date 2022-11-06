package ru.porcupine.taxidriver

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.taxidriver.ui.theme.Shapes
import ru.porcupine.taxidriver.ui.theme.TaxiDriverTheme

class EndActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val time = intent.getIntExtra("time", 0)
        var bestTime = SharedPreference(context = this@EndActivity).getValueInt("best")
        if(time>bestTime){
            bestTime=time
            SharedPreference(context = this@EndActivity).save("best", time)
        }
        setContent {
            BackgroundImage()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Current Time: $time",
                    modifier = Modifier
                        .padding(15.dp),
                    fontWeight = FontWeight(700),
                    fontSize = 18.sp,
                )
                Text(
                    text = "Best Time: $bestTime",
                    modifier = Modifier
                        .padding(15.dp),
                    fontWeight = FontWeight(700),
                    fontSize = 18.sp,
                )
                Row() {
                    IconButton(
                        onClick = {
                            val changePage = Intent(this@EndActivity, MainActivity::class.java)
                            startActivity(changePage)
                            finish()
                        },
                        modifier = Modifier
                            .padding(15.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ignition),
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(80.dp),
                            contentDescription = null,
                        )
                    }
                    IconButton(
                        onClick = {
                            val changePage = Intent(this@EndActivity, MenuActivity::class.java)
                            startActivity(changePage)
                            finish()
                        },
                        modifier = Modifier
                            .padding(15.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.fuel),
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(100.dp),
                            contentDescription = null,
                        )
                    }
                }

            }
        }
    }
    @Composable
    fun BackgroundImage() {
        Image(
            painter = painterResource(id = R.drawable.end_menu),
            contentDescription = "back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
