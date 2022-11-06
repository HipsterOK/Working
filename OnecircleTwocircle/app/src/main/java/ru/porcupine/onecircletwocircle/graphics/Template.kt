package ru.porcupine.onecircletwocircle.graphics

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.onecircletwocircle.R
import ru.porcupine.onecircletwocircle.controller.GameController

class Template {
    @Composable
    fun Button(gameController: GameController, text: String, h: Float, w: Float, font:Float, func: () -> Unit){
        Box(
            Modifier.padding(gameController.padding.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.button),
                contentDescription = "player border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (gameController.screenHeight / h).dp,
                        width = (gameController.screenWidth / w).dp
                    )
                    .clickable {
                        func.invoke()
                    },
                contentScale = ContentScale.FillBounds
                )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(gameController.font))),
                fontSize = (gameController.screenWidth / font).sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding((gameController.padding / 2).dp)
            )
        }
    }

    @Composable
    fun TemplateText(gameController: GameController, text: String, h: Float, w: Float, font:Float){
        Box(
            Modifier.padding(gameController.padding.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.text),
                contentDescription = "player border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (gameController.screenHeight / h).dp,
                        width = (gameController.screenWidth / w).dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(gameController.font))),
                fontSize = (gameController.screenWidth / font).sp,
                color = colorResource(id = R.color.green),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding((gameController.padding / 2).dp)
            )
        }
    }

    @Composable
    fun Background(){
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}