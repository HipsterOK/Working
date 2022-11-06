package ru.porcupine.catandbox

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ru.porcupine.catandbox.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var record = SharedPreference(context = this@MainActivity).getValueInt("record")

        setContent {

            val context = LocalContext.current

//            BackImage()
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {
                        val changePage = Intent(this@MainActivity, GameActivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                        .background(color = Color.Yellow, shape = Shapes.medium)


                ) {
                    Icon(
                        Icons.Filled.PlayArrow,
                        contentDescription = "Refresh Button",
                    )
                }
                Text(
                    text = "Record: $record",
                    modifier = Modifier
                        .padding(15.dp)
                )
            }
        }
    }
//    @Composable
//    fun BackImage() {
//        Image(
//            painter = painterResource(id = R.drawable.background),
//            contentDescription = "back",
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//    }
}