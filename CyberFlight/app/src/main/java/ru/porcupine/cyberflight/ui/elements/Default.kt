package ru.porcupine.cyberflight.ui.elements

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.cyberflight.R
import ru.porcupine.cyberflight.controllers.GameController
import ru.porcupine.cyberflight.data.Const

class Default {
    @Composable
    fun Button(gameController: GameController, text: String, h: Float, w: Float, font:Float, func: () -> Unit){
        Box(
            Modifier.padding(gameController.const.padding.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.button_background),
                contentDescription = "player border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (gameController.const.screenHeight / h).dp,
                        width = (gameController.const.screenWidth / w).dp
                    )
                    .clickable {
                        func.invoke()
                    },
                contentScale = ContentScale.FillBounds
                )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(gameController.const.font))),
                fontSize = (gameController.const.screenWidth / font).sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding((gameController.const.padding / 2).dp)
            )
        }
    }

    @Composable
    fun TemplateText(gameController: GameController, text: String, h: Float, w: Float, font:Float){
        Box(
            Modifier.padding(gameController.const.padding.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.text_background),
                contentDescription = "player border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (gameController.const.screenHeight / h).dp,
                        width = (gameController.const.screenWidth / w).dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(gameController.const.font))),
                fontSize = (gameController.const.screenWidth / font).sp,
                color = colorResource(id = R.color.red),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding((gameController.const.padding).dp)
            )
        }
    }

    @Composable
    fun Background(context:Context){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .border(
                width = (Const(context).screenWidth / 25).dp, color = colorResource(
                    id = R.color.red
                )
            ))
    }
}