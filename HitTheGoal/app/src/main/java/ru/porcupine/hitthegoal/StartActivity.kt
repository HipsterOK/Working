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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val record = SharedPreference(context = this@StartActivity).getValueInt("record")
        setContent {
            BackImage()
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        val changePage = Intent(this@StartActivity, MainActivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Text(
                        text = "Play",
                        fontWeight = FontWeight(700),
                        fontSize = 17.sp
                    )
                }
                Text(
                    text = "Record: $record",
                    modifier = Modifier
                        .padding(15.dp),
                    fontWeight = FontWeight(700),
                    fontSize = 20.sp,
                )
            }
        }
    }
    @Composable
    fun BackImage() {
        Image(
            painter = painterResource(id = R.drawable.start_background),
            contentDescription = "back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
    }
}