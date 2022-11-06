package ru.porcupine.economyquiz.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import ru.porcupine.economyquiz.R
import ru.porcupine.economyquiz.controller.MainController
import ru.porcupine.economyquiz.elements.Elements
import ru.porcupine.economyquiz.elements.MainElements

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainController = MainController(this)
        mainController.fillQuestion(application)
        mainController.startTimer(lifecycleScope, this)

        setContent {
            Elements().Background()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment =Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                MainElements().Counts(mainController = mainController)
                Text(
                    text = mainController.question,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontFamily = FontFamily(Font(mainController.font))),
                    fontSize = (mainController.screenWidth / 15).sp,
                    modifier = Modifier
                        .padding(mainController.padding.dp)
                )
                Box(Modifier.alpha(mainController.nextAlpha)) {
                    Elements().TemplateButton(
                        mainController = mainController,
                        icon = R.drawable.next,
                        size = 8
                    ) {
                        if (!mainController.active) {
                            if(mainController.id<mainController.listQuestion.size-1) {
                                mainController.nextQuestion()
                                mainController.active = true
                                mainController.nextAlpha = 0f
                            }
                        }
                    }
                }
                MainElements().Answers(mainController = mainController)
            }
            MainElements().Flash(mainController = mainController)
        }
    }
}