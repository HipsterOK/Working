package ru.porcupine.economyquiz.elements

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.economyquiz.functions.NewIntents
import ru.porcupine.economyquiz.R
import ru.porcupine.economyquiz.controller.MainController

class InfoElements {
    @Composable
   fun InfoView(mainController: MainController, context:Context) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.White)){
            Box(
                Modifier
                    .fillMaxSize()
                    .alpha(0.7f)) {
                Elements().Background()
            }
                Elements().TemplateButton(
                    mainController = mainController,
                    icon = R.drawable.home,
                    size = 6
                ) {
                    NewIntents().newIntentHome(context)
                }
            Text(
                text = "Hey, do you think yourself an economics expert? " +
                        "If yes, then here's a challenge for you. Take this Economics practice test quiz. " +
                        "Economics is a very interesting subject. " +
                        "It helps us deal with the problem of resource scarcity and how to deal with it efficiently. " +
                        "People who are an expert in this subject study the economic condition of society and know-how to make the most of the resources that are available to us. " +
                        "Try this quiz and refresh your concepts today!",
                textAlign = TextAlign.Center,
                fontSize = (mainController.screenHeight/35).sp,
                style = TextStyle(fontFamily = FontFamily(Font(mainController.font))),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(20.dp)
//                    .background(Color.White)
            )
        }
    }
}