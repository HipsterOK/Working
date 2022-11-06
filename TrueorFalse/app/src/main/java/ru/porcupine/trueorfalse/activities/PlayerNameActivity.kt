package ru.porcupine.trueorfalse.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.trueorfalse.Controller
import ru.porcupine.trueorfalse.R
import ru.porcupine.trueorfalse.SharedPreference

class PlayerNameActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val controller = Controller(this, application)
        val listPlayer = mutableStateListOf<String>()
        super.onCreate(savedInstanceState)
        setContent {
            Content(controller, listPlayer)
        }
    }
    @Composable
    fun Content(controller: Controller, listPlayer: SnapshotStateList<String>) {
        Background()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding((controller.screenWidth / 20).dp)
                .verticalScroll(rememberScrollState())
        ) {
            NamePlayersText(controller)
            for(i in 0 until controller.playerCount){
                listPlayer.add("")
                TextInput(controller, i, listPlayer)
            }
            NextButton(controller, listPlayer)
        }
    }

    @Composable
    private fun Background() {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }

    @Composable
    fun TextInput(controller: Controller, index: Int, listPlayer: SnapshotStateList<String>){
        val focusManager = LocalFocusManager.current
        TextField(
            value = listPlayer[index],
            singleLine = true,
            onValueChange = {
                    newText ->
                if(newText.length<15){
                    listPlayer[index] = newText
                }
                            },
            textStyle = TextStyle(color = Color.White ,fontSize = (controller.screenWidth / 20).sp, fontFamily = FontFamily(Font(controller.font))),
            placeholder = {
                Text(
                    "player ${index+1}",
                    style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                    fontSize = (controller.screenWidth / 20).sp,
                    color = Color.White,
                    modifier = Modifier
                        .alpha(0.7f)
                )
            },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        )
    }

    @Composable
    fun NamePlayersText(controller: Controller) {
        Box(modifier = Modifier
            .padding(bottom = (controller.screenWidth / 20).dp)) {
            Image(
                painter = painterResource(id = R.drawable.red_border),
                contentDescription = "play border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (controller.screenHeight / 11).dp,
                        width = (controller.screenWidth).dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Enter the names of the players",
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                fontSize = (controller.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun NextButton(controller: Controller, listPlayer: SnapshotStateList<String>) {
        Box(modifier = Modifier
            .padding(top = (controller.screenWidth / 20).dp)) {
            Image(
                painter = painterResource(id = R.drawable.red_border),
                contentDescription = "play border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (controller.screenHeight / 11).dp,
                        width = (controller.screenWidth / 5).dp
                    )
                    .clickable {
                        for(i in 0 until listPlayer.size){
                            if(listPlayer[i] == ""){
                                listPlayer[i] = "player ${i+1}"
                            }
                        }
                        val playerIntent = Intent(this@PlayerNameActivity, MainActivity::class.java)
                        SharedPreference(this@PlayerNameActivity).saveList("playersNames", listPlayer)
                        startActivity(playerIntent)
                        finish()
                    },
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Play",
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                fontSize = (controller.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}