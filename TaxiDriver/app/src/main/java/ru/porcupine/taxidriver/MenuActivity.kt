package ru.porcupine.taxidriver

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.taxidriver.ui.theme.Shapes
import ru.porcupine.taxidriver.ui.theme.TaxiDriverTheme

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bestTime = SharedPreference(context = this@MenuActivity).getValueInt("best")
        setContent {
          BackgroundImage()
            Box(Modifier.fillMaxSize()) {
                Column(
                    Modifier
                        .padding(top=100.dp)
                        .align(Alignment.TopCenter)
                ) {
                    Row(){
                        IconButton(
                            onClick = {
                                val changePage = Intent(this@MenuActivity, MainActivity::class.java)
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
                                    .size(100.dp),
                                contentDescription = null,
                            )
                        }

                        IconButton(
                            onClick = {
                                val changePage = Intent(this@MenuActivity, InstructionActivity::class.java)
                                startActivity(changePage)
                                finish()
                            },
                            modifier = Modifier
                                .padding(15.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.hands),
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(100.dp),
                                contentDescription = null,
                            )
                        }
                    }
                    Text(
                        text = "Best Time: \n" +
                                "$bestTime",
                        fontWeight = FontWeight(800),
                        fontSize = 18.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
//                            .background(Color.Black, Shapes.medium),
                    )

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun BackgroundImage() {
    Image(
        painter = painterResource(id = R.drawable.menu),
        contentDescription = "back",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}