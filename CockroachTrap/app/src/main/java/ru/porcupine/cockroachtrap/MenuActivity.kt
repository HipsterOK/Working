package ru.porcupine.cockroachtrap

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MenuActivity : ComponentActivity() {

    var difDelay = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainController = MainController(this@MenuActivity)

        val record = SharedPreference(context = this@MenuActivity).getValueInt("record")

        setContent {

            Content(mainController, record)
        }
    }

    @Composable
    fun Content(mainController: MainController, record: Int) {
        Background()
        Column {
            Cockroach()
            Play()
            Difficult()
            Record(mainController, record)
        }
    }

    @Composable
    fun Cockroach(){
        Image(
            painter = painterResource(id = R.drawable.cockroach),
            contentDescription = "icon",
            alignment = Center,
            modifier = Modifier
                .padding(20.dp)
        )
    }

    @Composable
    fun Difficult() {
        var expanded by remember { mutableStateOf(false) }
        var difficult by remember {mutableStateOf("Easy")}

        Box {
            IconButton(onClick = { expanded = true }) {
               Box {
                   Image(
                       painter = painterResource(id = R.drawable.borders_button_blue),
                       contentDescription = "",
                       alignment = Center,
                   )
                   Text(
                       text = "Difficult: $difficult",
                       textAlign = TextAlign.Center,
                       fontSize = 20.sp,
                       modifier = Modifier
                           .align(Center),
                       style = TextStyle(fontFamily = FontFamily(Font(R.font.flow_b)))
                   )
               }
            }
            DropdownMenu(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Box(
                    Modifier
                        .align(CenterHorizontally)
                        .fillMaxWidth()){
                    Image(
                        painter = painterResource(id = R.drawable.borders_button_blue),
                        contentDescription = "",
                        alignment = Center,
                        modifier = Modifier
                            .clickable(onClick = {
                               difficult = "Easy"
                                difDelay = 1000
                                expanded = false
                                Log.i("Delay", "Menu $difDelay")
                            })
                    )
                    Text("Easy", fontSize=20.sp,
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.flow_b))),
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Center)
                        )
                }
                Divider()
                Box(
                    Modifier
                        .align(CenterHorizontally)
                        .fillMaxWidth()){
                    Image(
                        painter = painterResource(id = R.drawable.borders_button_blue),
                        contentDescription = "",
                        alignment = Center,
                        modifier = Modifier
                            .clickable(onClick = {
                                difficult = "Hard"
                                difDelay=500
                                Log.i("Delay", "Menu $difDelay")
                                expanded = false
                            })
                    )
                    Text("Hard", fontSize=20.sp,
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.flow_b))),
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Center)
                       )
                }
            }
        }
    }

    @Composable
    fun Play() {
            IconButton(onClick = {
                if(SharedPreference(context = this@MenuActivity).getValueInt("first")==0) {
                    val instructionIntent = Intent(this@MenuActivity, InfoActivity::class.java)
                    instructionIntent.putExtra("delay", difDelay)
                    startActivity(instructionIntent)
                    finish()
                } else{
                    val mainIntent = Intent(this@MenuActivity, MainActivity::class.java)
                    mainIntent.putExtra("delay", difDelay)
                    startActivity(mainIntent)
                    finish()
                }
            }) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.borders_button_green),
                        contentDescription = "",
                        alignment = Center,
                    )
                    Text(
                        text = "Play",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.flow_b))),
                        modifier = Modifier
                            .align(Center)
                    )
                }
            }
    }

    @Composable
    fun Record(mainController: MainController, record: Int) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(50.dp), contentAlignment = Center) {
            Image(
                painter = painterResource(id = R.drawable.borders_button_red),
                contentDescription = "",
                modifier = Modifier
                    .align(Center)
                    .size(
                        width = mainController.screenWidth.dp / 2,
                        height = mainController.screenHeight.dp / 15
                    )
//                    .background(Color.Blue)
            )
            Text(
                text = "Record: $record",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.flow_b))),
                modifier = Modifier
                    .align(Center)
                    .size(
                        width = mainController.screenWidth.dp / 2,
                        height = mainController.screenHeight.dp / 29
                    )
//                    .background(Color.Cyan)
            )
        }
    }

    @Composable
    fun Background(){
        Image(
            painter = painterResource(id = R.drawable.menu_background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}