package ru.porcupine.fruitseeker

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class FinalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val result = intent.getIntExtra("time", 0)
        var record = SharedPreference(context = this@FinalActivity).getValueInt("record")
        if(result<record || record==0){
            record=result
            SharedPreference(context = this@FinalActivity).save("record", record)
        }

        setContent {
            BackgroundImage()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Time: ${TimeSeparator().timeToString(result)}",
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                        .fillMaxHeight(0.1f),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700)
                )
                Text(
                    text = "Best Time: ${TimeSeparator().timeToString(record)}",
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                        .fillMaxHeight(0.1f),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700)
                )
                Button(
                    onClick = {
                        val changePage = Intent(this@FinalActivity, GameActivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Text(text = "Play Again")
                }
                Button(
                    onClick = {
                        val changePage = Intent(this@FinalActivity, MainActivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Text(text = "Menu")
                }
            }
        }
    }

    @Composable
    fun BackgroundImage() {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}