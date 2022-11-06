package ru.porcupine.pizzadeliveryguy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.pizzadeliveryguy.ui.theme.Shapes

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val result = intent.getIntExtra("score", 0)
        var record = SharedPreference(context = this@ResultActivity).getValueInt("record")
        if(result>record){
            record=result
            SharedPreference(context = this@ResultActivity).save("record", result)
        }
        setContent {

            BackgroundImage()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Current Time: $result",
                    modifier = Modifier
                        .padding(15.dp)
                        .background(Color.LightGray, shape = Shapes.medium),
                    fontWeight = FontWeight(700),
                    fontSize = 18.sp,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.font)))
                )
                Text(
                    text = "Best Time: $record",
                    modifier = Modifier
                        .padding(15.dp)
                        .background(Color.LightGray, shape = Shapes.medium),
                    fontWeight = FontWeight(700),
                    fontSize = 18.sp,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.font)))
                )
                IconButton(
                    onClick = {
                        val changePage = Intent(this@ResultActivity, MainActivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.refresh),
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(80.dp),
                        contentDescription = null,
                    )
                }
                IconButton(
                    onClick = {
                        val changePage = Intent(this@ResultActivity, MainMenuAtivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.home),
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(80.dp),
                        contentDescription = null,
                    )
                }

            }
        }
    }
    @Composable
    fun BackgroundImage() {
        Image(
            painter = painterResource(id = R.drawable.back_menu),
            contentDescription = "back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
