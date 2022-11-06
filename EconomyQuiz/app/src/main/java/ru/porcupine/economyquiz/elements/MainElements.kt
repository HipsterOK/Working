package ru.porcupine.economyquiz.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import ru.porcupine.economyquiz.controller.MainController
import java.util.*
import kotlin.concurrent.timerTask

class MainElements {

    @Composable
    fun Counts(mainController: MainController){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Time(mainController= mainController)
            Score(mainController = mainController)
        }
    }

    @Composable
    fun Score(mainController: MainController){
        Elements().TemplateText(
            mainController = mainController,
            text = "Time: ${mainController.timer}",
            h = 14f,
            w = 3f
        )
    }

    @Composable
    fun Time(mainController: MainController){
        Elements().TemplateText(
            mainController = mainController,
            text = "Score: ${mainController.score}",
            h = 14f,
            w = 3f
        )
    }

    @Composable
    fun Answers(mainController: MainController){
        var index=0
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                for(i in 0 until 4) {
                        Answer(mainController = mainController, id = index)
                        index++
            }
        }
    }

    @Composable
    fun Answer(mainController: MainController, id:Int){
        var text = ""
        when (id) {
            0 -> text = mainController.answer1
            1 -> text = mainController.answer2
            2 -> text = mainController.answer3
            3 -> text = mainController.answer4
        }
        Box(
            modifier = Modifier
                .clickable {
                    if(mainController.active) {
                        mainController.active = false
                        mainController.nextAlpha = 1f
                        if (mainController.listQuestion[mainController.id].trueAnswer == text) {
                            mainController.score++
                            mainController.flashBack = Color.Green
                            mainController.flashAlpha = 0.3f
                            Timer().schedule(timerTask {
                                mainController.flashAlpha = 0f
                            }, 1000)
                        } else {
                            mainController.flashBack = Color.Red
                            mainController.flashAlpha = 0.3f
                            Timer().schedule(timerTask {
                                mainController.flashAlpha = 0f
                            }, 1000)
                            if (mainController.score >= 2) {
                                mainController.score -= 2
                            } else mainController.score = 0
                        }
                    }
            }
        ) {
            Elements().TemplateTextAnswer(
                mainController = mainController,
                text = text,
                h = 11.5f,
                w = 1f
            )
        }
    }

    @Composable
    fun Flash(mainController: MainController){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(mainController.flashAlpha)
                .background(mainController.flashBack)
        ){}
    }
}