package ru.porcupine.animalquiz

import android.content.Intent
import android.content.res.Resources
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import ru.porcupine.animalquiz.ui.theme.AnimalQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                val visTxt = remember { mutableStateOf(value = 0f) }

                BackImage()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cat),
                        contentDescription = "cat",
                        modifier = Modifier
                            .padding(15.dp)
                    )
                    Button(
                        onClick = {
                            val quizPage = Intent(this@MainActivity, QuizActivity::class.java)
                            startActivity(quizPage)
                            finish()
                        },
                        modifier = Modifier
                            .padding(15.dp)
                    ) {
                        Text(text = "Play", fontSize = 20.sp)
                    }

                    Button(
                        onClick = {
                            if (visTxt.value == 0f) {
                                visTxt.value = 1f
                            } else visTxt.value = 0f
                        },
                        modifier = Modifier
                            .padding(15.dp)
                    ) {
                        Text(text = "Info", fontSize = 20.sp)
                    }
                    Text(
                        text = "This is a quiz with questions about animals. \n" +
                                "Read the questions carefully and give the correct answers. \n" +
                                "The time of passing the test is not limited. \n" +
                                "If you select the correct answer, the button will turn green, otherwise red. \n" +
                                "To proceed to the next question, click \"Next\". \n" +
                                "Good luck!",
                        modifier = Modifier
                            .alpha(visTxt.value)
                            .padding(15.dp),
                        fontWeight = FontWeight(600),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
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