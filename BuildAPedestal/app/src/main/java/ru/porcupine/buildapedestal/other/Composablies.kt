package ru.porcupine.buildapedestal.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import ru.porcupine.buildapedestal.R

class Composablies {

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
    @Composable
    fun TemplateButton(gameController: GameController, text: String, h: Int, w: Int, func: () -> Unit) {
        Box(modifier = Modifier) {
            Image(
                painter = painterResource(id = R.drawable.text_border),
                contentDescription = "play border",
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
                fontSize = (gameController.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun TemplateTextField(gameController: GameController, text: String, h: Int, w: Double) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.text_border),
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
                fontSize = (gameController.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

}