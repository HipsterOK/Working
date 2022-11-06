package ru.porcupine.everyonetosleep

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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

class Elements {

    private var index = 0

    @Composable
    fun Background(image:Int){
        Image(
            painter = painterResource(id = image),
            contentDescription = "background",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
    }

    @Composable
    fun Window(gameLogic: Logic, id:Int, h:Float, w:Float){
        Image(
            painter = painterResource(id = gameLogic.windowImg[id]),
            contentDescription = "lightWindow",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(height = h.dp, width = w.dp)
                .padding((gameLogic.screenWidth / 60).dp)
                .clickable {
                    if (gameLogic.windowsLight[id] && gameLogic.timer > 0) {
                        gameLogic.windowsLight[id] = false
                        gameLogic.windowImg[id] = R.drawable.window_off
                        gameLogic.score++
                        gameLogic.count--
                    }
                }
        )
    }

    @Composable
    fun ScoreAndTime(gameLogic: Logic){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Time(gameLogic)
            Score(gameLogic)
        }
    }
    
    @Composable
    fun Time(gameLogic: Logic) {
        Box(modifier = Modifier) {
            Image(
                painter = painterResource(id = R.drawable.border),
                contentDescription = "play border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (gameLogic.screenHeight / 13).dp,
                        width = (gameLogic.screenWidth / 4).dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Time: ${gameLogic.timer}",
                textAlign = TextAlign.Center,
                color = Color.White,
                style = TextStyle(fontFamily = FontFamily(Font(gameLogic.font))),
                fontSize = (gameLogic.screenWidth / 20).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
    
    @Composable
    fun Score(gameLogic: Logic) {
        Box(modifier = Modifier) {
            Image(
                painter = painterResource(id = R.drawable.border),
                contentDescription = "play border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (gameLogic.screenHeight / 14).dp,
                        width = (gameLogic.screenWidth / 4.5).dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Score: ${gameLogic.score}",
                textAlign = TextAlign.Center,
                color = Color.White,
                style = TextStyle(fontFamily = FontFamily(Font(gameLogic.font))),
                fontSize = (gameLogic.screenWidth / 25).sp,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Buildings(gameLogic:Logic){
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Building1(gameLogic = gameLogic)
            Building2(gameLogic = gameLogic)
            Building3(gameLogic = gameLogic)
        }
    }

    @Composable
    fun Building1(gameLogic: Logic){
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .size(height = (gameLogic.screenHeight/1.5).dp, width = (gameLogic.screenWidth/4).dp)
        ){
          Image(
              painter = painterResource(id = R.drawable.building_1),
              contentDescription = "building_1",
              contentScale = ContentScale.FillBounds,
              modifier = Modifier
                  .size(height = (gameLogic.screenHeight/1.5).dp, width = (gameLogic.screenWidth/4).dp)
          )
             Row(
                 verticalAlignment = Alignment.CenterVertically,
                 horizontalArrangement = Arrangement.SpaceBetween
             ){
            for(i in 0 until 3){
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                 for(j in 0 until 13){
                         Window(gameLogic, id = index , h = (gameLogic.screenHeight/20), w = (gameLogic.screenWidth/15))
                         index++
                     }
                 }
                }
            }
        }
    }

    @Composable
    fun Building2(gameLogic: Logic){
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .size(height = (gameLogic.screenHeight/2.5).dp, width = (gameLogic.screenWidth/4).dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.building_2),
                contentDescription = "building_2",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(height = (gameLogic.screenHeight/1.5).dp, width = (gameLogic.screenWidth/4).dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                for(i in 0 until 3){
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        for(j in 0 until 7){
                            Window(gameLogic, id = index , h = (gameLogic.screenHeight/20), w = (gameLogic.screenWidth/15))
                            index++
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Building3(gameLogic: Logic){
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .size(height = (gameLogic.screenHeight/3).dp, width = (gameLogic.screenWidth/4).dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.building_3),
                contentDescription = "building_3",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(height = (gameLogic.screenHeight/1.5).dp, width = (gameLogic.screenWidth/4).dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                for(i in 0 until 3){
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        for(j in 0 until 6){
                            Window(gameLogic, id = index , h = (gameLogic.screenHeight/20), w = (gameLogic.screenWidth/15))
                            index++
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ButtonView(gameLogic: Logic, img:Int, h: Int, w: Int, func: () -> Unit) {
        Box(modifier = Modifier) {
            Image(
                painter = painterResource(id = img),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (gameLogic.screenHeight / h).dp,
                        width = (gameLogic.screenWidth / w).dp
                    )
                    .clickable {
                        func.invoke()
                    },
                contentScale = ContentScale.FillBounds
            )
        }
    }

    @Composable
    fun TextView(gameLogic: Logic, text: String, h: Int, w: Double) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.border),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (gameLogic.screenHeight / h).dp,
                        width = (gameLogic.screenWidth / w).dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(gameLogic.font))),
                fontSize = (gameLogic.screenWidth / 20).sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }

}