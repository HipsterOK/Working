package ru.porcupine.catchafish

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

class FinalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val result = intent.getIntExtra("score", 0)
        var record = SharedPreference(context = this@FinalActivity).getValueInt("record")
        if(result>record){
            record=result
            SharedPreference(context = this@FinalActivity).save("record", result)
        }
        setContent {

            BackgroundImage()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Score: $result",
                    modifier = Modifier
                        .padding(15.dp),
                    fontWeight = FontWeight(700),
                    fontSize = 18.sp
                )
                Text(
                    text = "Best Result: $record",
                    modifier = Modifier
                        .padding(15.dp),
                    fontWeight = FontWeight(700),
                    fontSize = 18.sp
                )
                Button(
                    onClick = {
                        val changePage = Intent(this@FinalActivity, MainActivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Text(text = "Play Again",
                        fontWeight = FontWeight(700),
                        fontSize = 18.sp
                    )
                }
                Button(
                    onClick = {
                        val changePage = Intent(this@FinalActivity, MenuActivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                  Text(
                      text = "Menu", fontWeight = FontWeight(700),
                      fontSize = 18.sp
                  )
                }

            }
        }
    }
    @Composable
    fun BackgroundImage() {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
