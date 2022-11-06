package ru.porcupine.meoworwoof

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.util.*
import kotlin.concurrent.timerTask

class GameElements {

    @Composable
    fun Background(){
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxSize()
        )
    }

    @Composable
    fun Hero(game: Game) {
        Box(modifier = Modifier.fillMaxSize()) {
            val pos by animateDpAsState(targetValue = game.heroPos.dp)
            Image(
                painter = painterResource(id = game.food),
                contentDescription = "hero",
                modifier = Modifier
                    .size(game.heroSize.dp)
                    .offset(x = pos, y = (game.screenHeight / 8).dp)
                    .align(Alignment.CenterStart)
                    .alpha(game.alphaHero)
            )
        }
    }

    @Composable
    fun Dog(game: Game) {
        val pos by animateDpAsState(targetValue = game.dogPos.dp)
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.dog),
                contentDescription = "hero",
                modifier = Modifier
                    .size(game.animal.dp)
                    .offset(x = pos, y = (-game.screenHeight / 8).dp)
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Cat(game: Game) {
        val pos by animateDpAsState(targetValue = game.catPos.dp)
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = "hero",
                modifier = Modifier
                    .size(game.animal.dp)
                    .offset(x = pos, y = (-game.screenHeight / 8).dp)
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Controller(game: Game){
        Row(Modifier.fillMaxSize()){
            Button(
                onClick = {
                    if(!game.wait) {
                        game.wait=true
                        game.heroPos = game.posHeroList[0]

                        if(
                            game.food == game.foodList[0] &&
                            game.dogPos == game.posAnimal[0]
                                ){
                            game.imgChoose = game.imgChooseList[0]
                            game.alphaChoose = 0.3f
                            Timer().schedule(timerTask {
                                game.alphaChoose=0f
                            }, 500)
                            game.score++
                        } else {
                            if(
                                game.food == game.foodList[1] &&
                                game.catPos == game.posAnimal[0]
                            ){
                                game.imgChoose = game.imgChooseList[0]
                                game.alphaChoose = 0.3f
                                Timer().schedule(timerTask {
                                    game.alphaChoose=0f
                                }, 500)
                                game.score++
                            } else {
                                game.imgChoose = game.imgChooseList[1]
                                game.alphaChoose = 0.3f
                                Timer().schedule(timerTask {
                                    game.alphaChoose=0f
                                }, 500)
                                if(game.score>=3) {
                                    game.score-=3
                                } else game.score = 0
                            }
                        }


                        Timer().schedule(timerTask {
                            game.alphaHero = 0f
                            game.heroPos = game.nullPosition
                        },500)
                        Timer().schedule(timerTask {
                            game.newHero()
                            game.alphaHero = 1f
                            game.wait=false
                        },1000)
                    }
                },
                Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight()
                    .alpha(0f)
            ){}
            Button(
                onClick = {
                    if(!game.wait) {
                        game.wait=true
                        game.heroPos = game.posHeroList[1]

                        if(
                            game.food == game.foodList[0] &&
                            game.dogPos == game.posAnimal[1]
                        ){
                            game.imgChoose = game.imgChooseList[0]
                            game.alphaChoose = 0.3f
                            Timer().schedule(timerTask {
                                game.alphaChoose=0f
                            }, 500)
                            game.score++
                        } else {
                            if(
                                game.food == game.foodList[1] &&
                                game.catPos == game.posAnimal[1]
                            ){
                                game.imgChoose = game.imgChooseList[0]
                                game.alphaChoose = 0.3f
                                Timer().schedule(timerTask {
                                    game.alphaChoose=0f
                                }, 500)
                                game.score++
                            } else {
                                game.imgChoose = game.imgChooseList[1]
                                game.alphaChoose = 0.3f
                                Timer().schedule(timerTask {
                                    game.alphaChoose=0f
                                }, 500)
                                if(game.score>=3) {
                                    game.score-=3
                                } else game.score = 0
                            }
                        }


                        Timer().schedule(timerTask {
                            game.alphaHero = 0f
                            game.heroPos = game.nullPosition
                        },500)
                        Timer().schedule(timerTask {
                            game.newHero()
                            game.alphaHero = 1f
                            game.wait=false
                        },1000)
                    }
                },
                Modifier
                    .fillMaxSize()
                    .alpha(0f)
            ){}
        }
    }

    @Composable
    fun Choose(game: Game){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(game.alphaChoose)
                .background(game.imgChoose)
        ){}
    }

    @Composable
    fun Timer(game: Game){
        Column(Modifier.fillMaxWidth().padding(game.padding.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
            Buttons().TextText(
                game = game,
                text = "Time: ${game.time}",
                h = 20f,
                w = 3.8f
            )
            Buttons().TextText(
                game = game,
                text = "Score: ${game.score}",
                h = 20f,
                w = 3.3f
            )
        }
    }
}