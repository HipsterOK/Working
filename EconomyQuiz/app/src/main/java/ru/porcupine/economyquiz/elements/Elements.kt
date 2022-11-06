package ru.porcupine.economyquiz.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.economyquiz.R
import ru.porcupine.economyquiz.controller.MainController

class Elements {
    @Composable
    fun TemplateButton(mainController: MainController, icon: Int, size: Int, func: () -> Unit){
        Box(
            Modifier.padding(mainController.padding.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.button_back),
                contentDescription = "button_back",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        (mainController.screenWidth / size).dp
                    )
                    .clickable {
                        func.invoke()
                    },
                contentScale = ContentScale.FillBounds
            )
            Image(
                painter = painterResource(id = icon),
                contentDescription = "button_icon",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        (mainController.screenWidth / (size + 2)).dp
                    ),
                contentScale = ContentScale.FillBounds
            )
        }
    }

    @Composable
    fun TemplateText(mainController: MainController, text: String, h: Float, w: Float){
        Box(
            Modifier.padding(mainController.padding.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.text),
                contentDescription = "player border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (mainController.screenHeight / h).dp,
                        width = (mainController.screenWidth / w).dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(mainController.font))),
                fontSize = (mainController.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun TemplateTextAnswer(mainController: MainController, text: String, h: Float, w: Float){
        Box(
            Modifier.padding((mainController.screenHeight/90).dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.text),
                contentDescription = "player border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (mainController.screenHeight / h).dp,
                        width = (mainController.screenWidth / w).dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(mainController.font))),
                fontSize = (mainController.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Background(){
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "background",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize()
        )
    }
}