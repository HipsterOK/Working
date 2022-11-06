package ru.porcupine.wherestheball

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.lifecycleScope
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : ComponentActivity() {

    private lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = Controller(this)
        setContent {
            Content(controller)
        }
    }

    @Composable
    fun Content(controller: Controller) {
        Background()
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Balance(controller)
            Cups(controller)
            Cost(controller)
        }
        Lose(controller)
    }

    @Composable
    fun Balance(controller: Controller) {
        Box(Modifier.padding(top = (controller.screenWidth/20).dp)){
            Image(
                painter = painterResource(id = R.drawable.blue_border),
                contentDescription = "border",
                modifier = Modifier
                    .size(
                        height = (controller.screenHeight / 9).dp,
                        width = (controller.screenWidth / 2).dp
                    )
                    .align(Alignment.Center),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Balance: ${controller.balance}",
                fontSize = (controller.screenHeight/30).sp,
                style = TextStyle(fontFamily = FontFamily(Font(R.font.small_print))),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Cups(controller: Controller) {
        Row {
            EmptyCup(controller, 0)
            BallCup(controller, 1)
            EmptyCup(controller, 2)
        }
    }

    @Composable
    fun EmptyCup(controller: Controller, i: Int) {
        val posX by animateDpAsState(targetValue = controller.cupX[i].dp)
        val posY by animateDpAsState(targetValue = controller.cupY[i].dp)
        Image(
            painter = painterResource(id = R.drawable.cup),
            contentDescription = "Cup",
            modifier = Modifier
                .size(controller.cupSize.dp)
                .offset(x = posX, y = posY)
                .clickable {
                    if (
                        controller.choose
                    ) {
                        controller.cupY[i] -= (controller.screenHeight / 10).toInt()
                        controller.choose = false
                        Timer().schedule(timerTask {
                            controller.cupY[i] += (controller.screenHeight / 10).toInt()
                            controller.gameShuffle = false
                            if (controller.set > controller.balance) {
                                controller.set = controller.balance
                            }
                            if (controller.balance <= 0) {
                                controller.gameShuffle=true
                                controller.lose = 1f
                                controller.menuBtn = true
                                controller.balance=500
                                controller.sharedPreference.save("balance", controller.balance)
                            }
                        }, 1000)
                    }
                }
        )
    }

    @Composable
    fun BallCup(controller: Controller, i: Int) {
        val posX by animateDpAsState(targetValue = controller.cupX[i].dp)
        val posY by animateDpAsState(targetValue = controller.cupY[i].dp)
        Box {
            Image(
                painter = painterResource(id = R.drawable.ball),
                contentDescription = "Cup",
                modifier = Modifier
                    .size((controller.cupSize / 3).dp)
                    .align(Alignment.Center)
                    .offset(x = posX)
            )
            Image(
                painter = painterResource(id = R.drawable.cup),
                contentDescription = "Cup",
                modifier = Modifier
                    .size(controller.cupSize.dp)
                    .align(Alignment.Center)
                    .offset(x = posX, y = posY)
                    .clickable {
                        if (
                            controller.choose
                        ) {
                            controller.cupY[1] -= (controller.screenHeight / 10).toInt()
                            controller.balance += 2 * controller.set
                            controller.choose = false
                            Timer().schedule(timerTask {
                                controller.cupY[1] += (controller.screenHeight / 10).toInt()
                                controller.gameShuffle = false
                                if(controller.balance>controller.bestBalance){
                                    controller.bestBalance = controller.balance
                                    SharedPreference(this@MainActivity).save("bestBalance", controller.bestBalance)
                                }
                            }, 1000)
                        }
                    }
            )
        }
    }

    @Composable
    fun Cost(controller: Controller) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.minus_icon),
                    contentDescription = "minus",
                    modifier = Modifier
                        .size((controller.cupSize / 2).dp)
                        .clickable {
                            if (
                                controller.set > 50 &&
                                !controller.gameShuffle &&
                                !controller.choose
                            ) {
                                controller.set -= 50
                            }
                        },
                )

                Box(Modifier.padding((controller.screenHeight/40).dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.yellow_border),
                        contentDescription = "border",
                        modifier = Modifier
                            .size(
                                height = (controller.screenHeight / 10).dp,
                                width = (controller.screenWidth / 3).dp
                            )
                            .align(Alignment.Center),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = "${controller.set}",
                        fontSize = (controller.screenHeight / 20).sp,
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.small_print))),
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.plus_icon),
                    contentDescription = "plus",
                    modifier = Modifier
                        .size((controller.cupSize / 2).dp)
                        .clickable {
                            if (
                                controller.set + 50 <= controller.balance &&
                                !controller.gameShuffle &&
                                !controller.choose
                            ) {
                                controller.set += 50
                            }
                        }
                )
            }
            Box(Modifier.padding((controller.screenHeight/40).dp)) {
                Image(
                    painter = painterResource(id = R.drawable.red_border),
                    contentDescription = "border",
                    modifier = Modifier
                        .size(
                            height = (controller.screenHeight / 10).dp,
                            width = (controller.screenWidth / 3).dp
                        )
                        .align(Alignment.Center)
                        .clickable {
                            if (
                                !controller.gameShuffle &&
                                !controller.choose
                            ) {
                                controller.startShuffle(lifecycleScope)
                                controller.balance -= controller.set
                            }
                        },
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    text = "SET",
                    fontSize = (controller.screenHeight / 20).sp,
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.small_print))),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
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
    fun Lose(controller: Controller) {
        Box(
            Modifier
                .fillMaxSize()
                .alpha(controller.lose)) {
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
                    style = TextStyle(fontFamily = FontFamily(Font(R.font.small_print))),
                    textAlign = TextAlign.Center,
                    fontSize = (Controller(this@MainActivity).screenWidth / 7).sp,)
                Box(
                    modifier = Modifier
                        .padding(25.dp)
                        .fillMaxWidth(),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.red_border),
                        contentDescription = "border play",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .clickable {
                                if (controller.menuBtn) {
                                    val gameIntent =
                                        Intent(this@MainActivity, MenuActivity::class.java)
                                    startActivity(gameIntent)
                                    finish()
                                }
                            }
                    )
                    Text(
                        text = "MENU",
                        style = TextStyle(fontFamily = FontFamily(Font(R.font.small_print))),
                        textAlign = TextAlign.Center,
                        fontSize = (Controller(this@MainActivity).screenWidth / 7).sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }

    override fun onPause() {
        if(controller.balance>0) {
            val sharedPreference = SharedPreference(this)
            sharedPreference.save("balance", controller.balance)
        }
        super.onPause()
    }
}