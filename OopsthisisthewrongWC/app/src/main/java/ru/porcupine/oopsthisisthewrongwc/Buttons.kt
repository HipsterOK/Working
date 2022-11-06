package ru.porcupine.oopsthisisthewrongwc

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Buttons {
    @Composable
    fun ButtonText(game: Game, text: String, h: Float, w: Float, func: () -> Unit) {
        Box(Modifier.padding(game.padding.dp)) {
            Image(
                painter = painterResource(id = R.drawable.orange_border),
                contentDescription = "border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (game.screenHeight / h).dp,
                        width = (game.screenWidth / w).dp
                    )
                    .clickable {
                        func.invoke()
                    },
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontFamily = FontFamily(Font(game.font))),
                fontSize = (game.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun TextText(game: Game, text: String, h: Float, w: Float) {
        Box(Modifier.padding(game.padding.dp)) {
            Image(
                painter = painterResource(id = R.drawable.blue_border),
                contentDescription = "border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (game.screenHeight / h).dp,
                        width = (game.screenWidth / w).dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(game.font))),
                fontSize = (game.screenWidth / 20).sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}