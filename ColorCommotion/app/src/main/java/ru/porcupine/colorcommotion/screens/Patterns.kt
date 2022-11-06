package ru.porcupine.colorcommotion.screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
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
import ru.porcupine.colorcommotion.R
import ru.porcupine.colorcommotion.controllers.GameController

class Patterns(val context: Context) {
    @Composable
    fun TextPattern(text:String, gameController: GameController, color: Color = Color.White){
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = FontFamily(Font(gameController.constants.font))),
            fontSize = (gameController.constants.textSize).sp,
            color = color,
            modifier = Modifier
                .padding((gameController.constants.padding).dp)
        )
    }

    @Composable
    fun OneColorText(gameController: GameController, id:Int){
       Card(
           backgroundColor = gameController.variables.arrayBackgroundColor[id],
           border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .size(width = (gameController.constants.screenWidth/3.5).dp,
                (gameController.constants.screenHeight/11).dp)
        ){
            Patterns(context).TextPattern(
                text = gameController.variables.arrayTextColor[id],
                gameController = gameController,
                color = gameController.variables.arrayColor[id]
            )
        }
    }

    @Composable
    fun OneColorButton(gameController: GameController, id: Int){
        Card(
            backgroundColor = gameController.variables.arrayButtonColor[id],
            elevation = (gameController.constants.screenWidth/30).dp,
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .size((gameController.constants.screenWidth / 6).dp)
                .padding((gameController.constants.padding / 3).dp)
                .clickable {
                    gameController.checkTap(id)
                }
        ) {}
    }

    @Composable
    fun Background(){
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }

    @Composable
    fun Person(image:Int, gameController: GameController){
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = Modifier
                .size(height = (gameController.constants.screenHeight/4).dp, width = (gameController.constants.screenWidth/3).dp)
        )
    }

    @Composable
    fun ButtonPattern(gameController: GameController, text: String, func: () -> Unit){
        Card(
            Modifier
                .padding(gameController.constants.padding.dp)
                .clickable {
                    func.invoke()
                },
            elevation = (gameController.constants.screenWidth/30).dp,
            backgroundColor = Color.Magenta
        ) {
            TextPattern(
                text = text,
                gameController = gameController
            )

        }
    }
}