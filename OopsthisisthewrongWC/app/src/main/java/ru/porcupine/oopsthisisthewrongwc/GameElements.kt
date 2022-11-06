package ru.porcupine.oopsthisisthewrongwc

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
    fun Hero(game:Game) {
        Box(modifier = Modifier.fillMaxSize()) {
            val pos by animateDpAsState(targetValue = game.heroPos.dp)
            Image(
                painter = painterResource(id = game.sex),
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
    fun WCMan(game:Game) {
        val pos by animateDpAsState(targetValue = game.manWCPos.dp)
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.wc_man),
                contentDescription = "hero",
                modifier = Modifier
                    .size(game.WCSign.dp)
                    .offset(x = pos, y = (-game.screenHeight / 8).dp)
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun WCWoman(game:Game) {
        val pos by animateDpAsState(targetValue = game.womanWCPos.dp)
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.wc_woman),
                contentDescription = "hero",
                modifier = Modifier
                    .size(game.WCSign.dp)
                    .offset(x = pos, y = (-game.screenHeight / 8).dp)
                    .align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Controller(game:Game){
        Row(Modifier.fillMaxSize()){
            Button(
                onClick = {
                    if(!game.wait) {
                        game.wait=true
                        game.heroPos = game.posHeroList[0]

                        if(
                            game.sex == game.sexList[0] &&
                            game.manWCPos == game.posWC[0]
                                ){
                            game.imgChoose = game.imgChooseList[0]
                            game.alphaChoose = 0.3f
                            Timer().schedule(timerTask {
                                game.alphaChoose=0f
                            }, 500)
                            game.score++
                        } else {
                            if(
                                game.sex == game.sexList[1] &&
                                game.womanWCPos == game.posWC[0]
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
                            game.sex == game.sexList[0] &&
                            game.manWCPos == game.posWC[1]
                        ){
                            game.imgChoose = game.imgChooseList[0]
                            game.alphaChoose = 0.3f
                            Timer().schedule(timerTask {
                                game.alphaChoose=0f
                            }, 500)
                            game.score++
                        } else {
                            if(
                                game.sex == game.sexList[1] &&
                                game.womanWCPos == game.posWC[1]
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
    fun Timer(game:Game){
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