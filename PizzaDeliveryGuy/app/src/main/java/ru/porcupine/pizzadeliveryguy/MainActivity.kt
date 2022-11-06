package ru.porcupine.pizzadeliveryguy

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rdControl = RoadController(this)
        val mainControl = MainController(this)
        val carsControl = CarsController(this)

        mainControl.startTimer()
        rdControl.startCoroutine(mainControl)
        carsControl.spawn(mainControl)

        setContent {
            Content(rdControl, mainControl, carsControl)
        }
    }

    @Composable
    fun Content(rdControl: RoadController, mainControl: MainController, carsControl: CarsController) {
        Road(rdControl)
        Control(mainControl)
        Player(mainControl)
        InfoBar(mainControl)
        Cars(carsControl)
    }

    @Composable
    fun InfoBar(mainControl: MainController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, top = 5.dp),
            horizontalAlignment =Alignment.CenterHorizontally,
            verticalArrangement =Arrangement.Top
        ) {
            Text(
                text = "${mainControl.timer}",
                fontSize = (mainControl.screenHeight/19).sp,
                fontWeight = FontWeight.W700,
                color = Color.White,
                fontStyle = FontStyle(R.font.font),
                style = TextStyle(shadow = Shadow(Color.Black , Offset(5.0f, 5.0f), 1.0f))
            )
        }
    }

    @Composable
    fun Car(carControl:CarsController, id:Int){
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = carControl.carList[id].painterId),
                contentDescription = "car$id",
                modifier = Modifier
                    .absolutePadding(
                        bottom = carControl.carYPadding[id].dp,
                        left = carControl.paddingXList[id].dp,
                        top = carControl.carYPaddingTop[id].dp
                    )
                    .size(width = carControl.carWidth.dp, height = carControl.carHight.dp)
                    .align(Alignment.BottomStart)
//                    .background(Color.Cyan),

            )
        }
    }

    @Composable
    fun Cars(carControl: CarsController) {
        Car(carControl, id = 0)
        Car(carControl, id = 1)
        Car(carControl, id = 2)
        Car(carControl, id = 3)
    }

        @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun Control(mainControl: MainController) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            var pressed1 by remember { mutableStateOf(false) }
            var pressed2 by remember { mutableStateOf(false) }
            var enable1 by remember { mutableStateOf(true) }
            var enable2 by remember { mutableStateOf(true) }

            Button(
                onClick = {

                },
                modifier = Modifier
                    .pointerInteropFilter {
                        pressed1 = when (it.action) {
                            MotionEvent.ACTION_DOWN -> true
                            MotionEvent.ACTION_MOVE -> true
                            else -> false
                        }
                        true
                    }
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
                    .alpha(0f),
                enabled = enable1

            ){
                LaunchedEffect(pressed1 && enable1 && mainControl.gameEnbl) {
                    enable2 = false
                    while (pressed1 && mainControl.playerX>=(5).toInt()) {
                        mainControl.playerX-=2
                        delay(2)
                    }
                    enable2 = true
                }
            }

            Button(
                onClick = {
//                viewModel.catX+=(viewModel.dpWidth/20).toInt()
                },
                modifier = Modifier
                    .pointerInteropFilter {
                        pressed2 = when (it.action) {
                            MotionEvent.ACTION_DOWN -> true
                            MotionEvent.ACTION_MOVE -> true
                            else -> false
                        }
                        true
                    }
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .alpha(0f),
                enabled = enable2
            ){
                LaunchedEffect(pressed2 && enable2 && mainControl.gameEnbl) {
                    enable1 = false
                    while (pressed2 && mainControl.playerX<(mainControl.screenWidth.toInt()-mainControl.playerWidth.toInt())) {
                        mainControl.playerX+=2
                        delay(2)
                    }
                    enable1 = true
                }
            }
        }
    }

    @Composable
    fun Player(mainControl: MainController) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Image(
                painter = painterResource(R.drawable.scooter),
                contentDescription = "player",
                modifier = Modifier
                    .padding(start = mainControl.playerX.dp, bottom = 5.dp)
//                .background(Color.Cyan)
                    .size(width = mainControl.playerWidth.dp, height = mainControl.playerHeight.dp)
                    .align(Alignment.BottomStart)
            )
        }
    }

    @Composable
    fun Road(rdControl: RoadController) {
        Image(
            painter = painterResource(id = R.drawable.road),
            contentDescription = "road",
            modifier = Modifier
                .fillMaxSize()
                .absolutePadding(bottom = rdControl.pad2.dp, top = rdControl.pad2Top.dp),
            contentScale = ContentScale.FillWidth
        )
        Image(
            painter = painterResource(id = R.drawable.road),
            contentDescription = "road",
            modifier = Modifier
                .fillMaxSize()
                .absolutePadding(bottom = rdControl.pad1.dp, top = rdControl.pad1Top.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}