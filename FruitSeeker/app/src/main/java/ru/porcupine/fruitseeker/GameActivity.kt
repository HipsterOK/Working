package ru.porcupine.fruitseeker

import android.content.Intent
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.porcupine.fruitseeker.ui.theme.Shapes
import java.util.*
import kotlin.concurrent.timerTask

class GameActivity : ComponentActivity() {

    private var timer = -3
    private var listFruitModel:MutableList<FruitModel> = arrayListOf()
    private var openId1 = -1
    private var openId2 = -1
    private var firstId = -1
    private var running = false
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listFruitModel = FruitAdapter().fillListFruit()
        listFruitModel+=FruitAdapter().fillListFruit()
        listFruitModel.shuffle()

        timer = -3

        setContent {
            val coroutineScope = rememberCoroutineScope()
            val isOpen:MutableList<MutableState<Boolean>> = MutableList(24){ remember { mutableStateOf(value = true)}}
            val timeV= remember { mutableStateOf(value = timer)}
            var index = 0

            LaunchedEffect(key1 = Unit) {
                coroutineScope.launch {
                    while(score<12){
                        timer++
                        if(timer>0){
                            timeV.value=timer
                        } else timeV.value=0
                        if(timer==1){
                            for(i in 0..23){
                                isOpen[i].value=false
                            }
                        }
                        delay(1000)
                    }
                    startActivity(Intent(this@GameActivity, FinalActivity::class.java).putExtra("time",timer))
                    finish()
                }
            }

            BackgroundImage()
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Time ${TimeSeparator().timeToString(timeV.value)}",
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                        .fillMaxHeight(0.1f),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    fontWeight = FontWeight(700)
                )
                for(i in 0..5){
                    Row(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        for(j in 0..3){
                                FruitCard(index, listFruitModel[index], isOpen[index], isOpen)
                                index++
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun FruitCard(
        id: Int,
        fruitModel: FruitModel,
        isOpenState: MutableState<Boolean>,
        isOpen: MutableList<MutableState<Boolean>>
    ) {
            IconButton(
                onClick = {
                    if(!running && !isOpenState.value) {
                        isOpenState.value = true
                        if (openId1 == -1) {
                            firstId = id
                            openId1 = fruitModel.id
                        } else {
                            openId2 = fruitModel.id
                            if (openId1 != openId2) {
                                running=true
                                Timer().schedule(timerTask {
                                    isOpenState.value = false
                                    isOpen[firstId].value = false
                                    openId1 = -1
                                    openId2 = -1
                                    running=false
                                }, 1000)
                            } else {
                                score++
                                openId1 = -1
                                openId2 = -1
                                Log.i("Score:", score.toString())
                            }
                        }
                    }

                },
                modifier = Modifier
                    .padding(5.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = Shapes.medium,
                        clip = true
                    ),
            ) {
                if(isOpenState.value) {
                    Image(
                        painter = painterResource(id = fruitModel.img),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(70.dp)
                            .background(Color.White, shape = Shapes.medium)
                            .border(1.dp, Color.Gray, Shapes.medium)
                    )
                } else{
                    Image(
                        painter = painterResource(id = R.drawable.back_card),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(70.dp)
//                            .border(1.dp, Color.DarkGray, shape = Shapes.small)
                            .background(Color.White, Shapes.medium)
                            .border(1.dp, Color.Gray, Shapes.medium)
                    )
                }
            }
    }
    @Composable
    fun BackgroundImage() {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
