package ru.porcupine.trueorfalse.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import ru.porcupine.trueorfalse.Controller
import ru.porcupine.trueorfalse.R
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : ComponentActivity() {

    private var colorTF by mutableStateOf(Color.Green)
    private var alphaTF by mutableStateOf(0f)
    var alphaNext by mutableStateOf(0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val controller = Controller(this, application)

        controller.startGame()
        controller.startQuestion(this, lifecycleScope,this@MainActivity)

        setContent {
           Content(controller)
        }
    }

    @Composable
    fun Content(controller: Controller) {
        Background()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding((controller.screenWidth / 20).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Player(controller)
                Time(controller)
            }

            TextCenter(controller)
            Answers(controller)
        }
        BackView()
        NextPlayer(controller)
    }

    @Composable
    private fun NextPlayer(controller: Controller) {
        var turn by remember {mutableStateOf(0)}
        turn = if(controller.turnId+1>=controller.playerCount){
            0
        } else controller.turnId+1
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alphaNext)
                .background(Color.Black)

        ){
            Text(
                    text = "Next question for ${controller.playerList[turn]}",
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                    fontSize = (controller.screenWidth / 16).sp,
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color.White
            )
        }
    }

    @Composable
    private fun Player(controller: Controller) {
        Box(Modifier.padding((controller.screenWidth / 20).dp)) {
            Image(
                painter = painterResource(id = R.drawable.cyan_border),
                contentDescription = "player border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (controller.screenHeight / 11).dp,
                        width = (controller.screenWidth).dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "${controller.playersView}'s turn",
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                fontSize = (controller.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    private fun Time(controller: Controller) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.cyan_border),
                contentDescription = "player border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (controller.screenHeight / 11).dp,
                        width = (controller.screenWidth / 5).dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "${controller.timer}",
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                fontSize = (controller.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    private fun TextCenter(controller: Controller) {
        Text(
            text = controller.textView,
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
            fontSize = (controller.screenWidth / 20).sp,
            modifier = Modifier,
            color = Color.White
        )
    }

    @Composable
    private fun Answers(controller: Controller) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
//            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.true_button),
                contentDescription = "true",
                modifier = Modifier
                    .size(
                        height = ((controller.screenHeight) / 7).dp,
                        width = ((controller.screenWidth)).dp
                    )
                    .padding((controller.screenWidth / 30).dp)
                    .clickable {
                        if (controller.answer) {
                            controller.answer = false
                            if (controller.question[controller.questionId].trueAnswer) {
                                controller.score[controller.turnId]++
                                colorTF = Color.Green
                                alphaTF = 0.3f
                                if(controller.score[controller.turnId]>=20){
                                    controller.gameEnable = false
                                }
                                controller.textView =
                                    controller.question[controller.questionId].wrongAnswer
                                Timer().schedule(timerTask {
                                    alphaTF = 0f
                                    if(controller.questionId<controller.question.size-1) {
                                        Timer().schedule(timerTask {
                                            alphaNext = 0f
                                            controller.nextQuest()
                                        }, 2000)
                                        alphaNext = 1f
                                    }
                                }, 2000)

                            } else {
                                colorTF = Color.Red
                                alphaTF = 0.3f
                                controller.textView =
                                    controller.question[controller.questionId].wrongAnswer
                                Timer().schedule(timerTask {
                                    alphaTF = 0f
                                    if(controller.questionId<controller.question.size-1) {
                                        Timer().schedule(timerTask {
                                            alphaNext = 0f
                                            controller.nextQuest()
                                        }, 2000)
                                        alphaNext = 1f
                                    }
                                }, 2000)

                            }
                        }
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.false_button),
                contentDescription = "false",
                modifier = Modifier
                    .size(
                        height = ((controller.screenHeight) / 7).dp,
                        width = ((controller.screenWidth)).dp
                    )
                    .padding((controller.screenWidth / 30).dp)
                    .clickable {
                        if (controller.answer) {
                            controller.answer = false
                            if (!controller.question[controller.questionId].trueAnswer) {
                                controller.score[controller.turnId]++
                                colorTF = Color.Green
                                alphaTF = 0.3f
                                if(controller.score[controller.turnId]>=20){
                                    controller.gameEnable = false
                                }
                                controller.textView =
                                    controller.question[controller.questionId].wrongAnswer
                                Timer().schedule(timerTask {
                                    alphaTF = 0f
                                    if(controller.questionId<controller.question.size-1) {
                                        Timer().schedule(timerTask {
                                            alphaNext = 0f
                                            controller.nextQuest()
                                        }, 2000)
                                        alphaNext = 1f
                                    }
                                }, 2000)


                            } else {
                                colorTF = Color.Red
                                alphaTF = 0.3f
                                controller.textView =
                                    controller.question[controller.questionId].wrongAnswer
                                Timer().schedule(timerTask {
                                    alphaTF = 0f
                                    if(controller.questionId<controller.question.size-1) {
                                        Timer().schedule(timerTask {
                                            alphaNext = 0f
                                            controller.nextQuest()
                                        }, 2000)
                                        alphaNext = 1f
                                    }
                                }, 2000)

                            }
                        }
                    }
            )
        }
    }

    @Composable
    private fun BackView() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alphaTF)
                .background(colorTF)

        )
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
}