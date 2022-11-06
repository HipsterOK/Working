package ru.porcupine.catandbox

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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.porcupine.catandbox.ui.theme.Shapes

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
                        .padding(15.dp)
                )
                Text(
                    text = "Record: $record",
                    modifier = Modifier
                        .padding(15.dp)
                )
                IconButton(
                    onClick = {
                        val changePage = Intent(this@ResultActivity, GameActivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                        .background(color = Color.Yellow, shape = Shapes.medium)
                ) {
                    Icon(
                        Icons.Filled.Refresh,
                        contentDescription = "Play Again",
                    )
                }
                IconButton(
                    onClick = {
                        val changePage = Intent(this@ResultActivity, MainActivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                        .background(color = Color.Yellow, shape = Shapes.medium)
                ) {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "Main Menu",
                    )
                }
            }
        }
    }
    @Composable
    fun BackImage() {
//        Image(
//            painter = painterResource(id = R.drawable.background),
//            contentDescription = "back",
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
    }
}
