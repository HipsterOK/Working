package ru.porcupine.animalquiz

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.animalquiz.ui.theme.Shapes

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val succes = intent.getIntExtra("succes", 0)
        val wrong = intent.getIntExtra("wrong", 0)
        setContent {
            BackImage()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Correct answers: $succes",
                    modifier = Modifier
                        .padding(15.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                )
                Text(
                    text = "Incorrect answers: $wrong",
                    modifier = Modifier
                        .padding(15.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                )
               Button(
                    onClick = {
                        val changePage = Intent(this@ResultActivity, QuizActivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                   Text(text = "Try again", fontSize = 20.sp)
                }
                Button(
                    onClick = {
                        val changePage = Intent(this@ResultActivity, MainActivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Text(text = "Main menu", fontSize = 20.sp)
                }
            }
        }
    }
    @Composable
    fun BackImage() {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}