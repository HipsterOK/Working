package ru.porcupine.dodgethelightning

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Templates {
    @Composable
    fun TemplateButton(controller: Controller, text: String, h: Int, w: Int, func: () -> Unit) {
        Box(modifier = Modifier) {
            Image(
                painter = painterResource(id = R.drawable.blue_border),
                contentDescription = "play border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (controller.screenHeight / h).dp,
                        width = (controller.screenWidth / w).dp
                    )
                    .clickable {
                        func.invoke()
                    },
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                color = Color.White,
                style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                fontSize = (controller.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun TemplateTextField(controller: Controller, text: String, h: Int, w: Double) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.green_border),
                contentDescription = "player border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (controller.screenHeight / h).dp,
                        width = (controller.screenWidth / w).dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(controller.font))),
                fontSize = (controller.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Background(back: Int) {
        Image(
            painter = painterResource(id = back),
            contentDescription = "background",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}