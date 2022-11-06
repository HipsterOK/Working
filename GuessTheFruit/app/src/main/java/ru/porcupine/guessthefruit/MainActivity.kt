package ru.porcupine.guessthefruit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val control = Controller(this)

        control.fillCharAlpha()

        setContent {
            Content(control)
        }
    }

    @Composable
    fun Content(control: Controller) {
        Background()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {
            Word(control)
            HpView(control)
            Alphabet(control)
        }
        Win(control)
        Lose(control)
    }

    @Composable
    fun HpView(control: Controller) {
        Row {
            for(i in 0..6){
                Hp(control, i)
            }
        }
    }

    @Composable
    fun Hp(control: Controller, i: Int) {
        Image(
            painterResource(id = R.drawable.hp),
            contentDescription = "1hp",
            modifier = Modifier
                .size(control.hpSize.dp)
                .alpha(control.hpAlpha[i])
        )
    }

    @Composable
    fun Word(control: Controller) {
        Row() {
        for(i in 0 until control.chars.size){

                CharWord(control, i)
            }
        }
    }

    @Composable
    fun CharWord(control: Controller, i: Int) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
            .padding((control.screenHeight / 150).dp)
        )
        {
            Text(
                text = "${control.chars[i]}",
                style = TextStyle(fontFamily = FontFamily(Font(R.font.burst_my_bubble))),
                fontSize = (control.screenWidth/(control.chars.size+4)).sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .alpha(control.charAlpha[i])
            )
            Image(
                painter = painterResource(id = R.drawable.proch),
                contentDescription = "border",
                modifier = Modifier
                    .size((control.screenWidth/(control.chars.size+4)).dp)

            )
        }
    }

    @Composable
    fun Alphabet(control: Controller) {
        var index = 0
        Column() {
            for(i in 0..3){
                Row {
                    for(j in 0..5){
                        Char(control, index)
                        index++
                    }
                }
            }
        }
        Column() {
                Row {
                    for(j in 0..1) {
                        Char(control, index)
                        index++
                    }
                }
        }
    }

    @Composable
    fun Char(control: Controller, index: Int) {
        Box(modifier = Modifier
            .padding((control.screenHeight / 150).dp)
            .alpha(control.alphabetAlpha[index])
            )
        {

            Image(
                painter = painterResource(id = R.drawable.char_border),
                contentDescription = "border char",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size((control.charSize * 1.1).dp)
                    .clickable {
                        if (control.alphabetEnabled[index] && control.gameEnabled) {
                            control.alphabetAlpha[index] = 0f
                            control.alphabetEnabled[index] = false
                            for (i in 0 until control.chars.size) {
                                if (control.alphabet[index] == control.chars[i]) {
                                    control.charAlpha[i] = 1f
                                    control.count++
                                    control.find = true
                                }
                            }
                            if (!control.find) {
                                control.hp--
                                control.hpAlpha[control.hp] = 0f
                            }
                            control.find = false
                            if (control.hp == 0) {
                                control.gameEnabled = false
                                Timer().schedule(timerTask {
                                    control.lose = 1f
                                    control.menuBtn=true
                                }, 1000)
                            }
                            if (control.count == control.chars.size) {
                                control.gameEnabled = false
                                Timer().schedule(timerTask {
                                    control.win = 1f
                                    control.menuBtn = true
                                }, 1000)

                            }
                        }
                        Log.i("ID", index.toString())
                    }

            )

            Text(
                text = "${control.alphabet[index]}",
                style = TextStyle(fontFamily = FontFamily(Font(R.font.burst_my_bubble))),
                fontSize = control.charSize.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                )
        }

    }

    @Composable
    fun Background() {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }

    @Composable
    fun Win(control: Controller) {
        Box(Modifier.fillMaxSize().alpha(control.win)) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.align(Alignment.Center)) {
                Text(text = "YOU WIN",
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.burst_my_bubble))),
                    textAlign = TextAlign.Center,
                    fontSize = (Controller(this@MainActivity).screenWidth / 7).sp,)
                Box(
                    modifier = Modifier
                        .padding(25.dp)
                        .fillMaxWidth(),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.border_1),
                        contentDescription = "border play",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .clickable {
                                if(control.menuBtn) {
                                    val gameIntent =
                                        Intent(this@MainActivity, MenuActivity::class.java)
                                    startActivity(gameIntent)
                                    finish()
                                }
                            }
                    )
                    Text(
                        text = "MENU",
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.burst_my_bubble))),
                        textAlign = TextAlign.Center,
                        fontSize = (Controller(this@MainActivity).screenWidth / 7).sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }

    @Composable
    fun Lose(control: Controller) {
        Box(Modifier.fillMaxSize().alpha(control.lose)) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)) {
                Text(text = "YOU LOST",
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.burst_my_bubble))),
                    textAlign = TextAlign.Center,
                    fontSize = (Controller(this@MainActivity).screenWidth / 7).sp,)
                Box(
                    modifier = Modifier
                        .padding(25.dp)
                        .fillMaxWidth(),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.border_1),
                        contentDescription = "border play",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .clickable {
                                if(control.menuBtn) {
                                    val gameIntent =
                                        Intent(this@MainActivity, MenuActivity::class.java)
                                    startActivity(gameIntent)
                                    finish()
                                }
                            }
                    )
                    Text(
                        text = "MENU",
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.burst_my_bubble))),
                        textAlign = TextAlign.Center,
                        fontSize = (Controller(this@MainActivity).screenWidth / 7).sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
