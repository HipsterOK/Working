package ru.porcupine.hitthegoal

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val result = intent.getIntExtra("result", 0)
        var record = SharedPreference(context = this@ResultActivity).getValueInt("record")
        if(result>record){
            record=result
            SharedPreference(context = this@ResultActivity).save("record", result)
        }
        setContent {
            BackImage()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Result: $result",
                    modifier = Modifier
                        .padding(15.dp),
                    fontWeight = FontWeight(700),
                    fontSize = 17.sp
                )
                Text(
                    text = "Record: $record",
                    modifier = Modifier
                        .padding(15.dp),
                    fontWeight = FontWeight(700),
                    fontSize = 17.sp
                )
                Button(
                    onClick = {
                        val changePage = Intent(this@ResultActivity, MainActivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Text(
                        text = "Play Again",
                        fontWeight = FontWeight(700),
                        fontSize = 17.sp
                    )
                }
                Button(
                    onClick = {
                        val changePage = Intent(this@ResultActivity, StartActivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                   Text(
                        text = "Main Menu",
                       fontWeight = FontWeight(700),
                       fontSize = 17.sp
                    )
                }
            }
        }
    }
    @Composable
    fun BackImage() {
        Image(
            painter = painterResource(id = R.drawable.result_background),
            contentDescription = "back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
    }
}