package ru.porcupine.catchafish

import android.app.Activity
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
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {

    var thisActivity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        thisActivity = this

        val viewModel = MainActivityView(this@MainActivity)
        val viewFish = FishViewModel(this@MainActivity)

        viewModel.startTimer(this@MainActivity, viewFish)
        viewFish.spawn(viewModel)

        setContent {
            Content(viewModel, viewFish)
        }
    }

    fun finishActiv() {
        this.finish()
        thisActivity = null
    }
}

@Composable
fun Content(viewModel: MainActivityView, viewFish: FishViewModel) {
    BackgroundImage()
    CountAndHP(viewModel, viewFish)
    Control(viewModel)
    Cat(viewModel)
    FishBand(viewFish)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Control(viewModel: MainActivityView) {
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
            LaunchedEffect(pressed1 && enable1) {
                enable2 = false
                viewModel.painterId = R.drawable.cat_left
                while (pressed1 && viewModel.catX>=(5).toInt()) {
                    viewModel.catX-=2
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
            LaunchedEffect(pressed2 && enable2) {
                enable1 = false
                viewModel.painterId = R.drawable.cat_right
                while (pressed2 && viewModel.catX<(viewModel.dpWidth.toInt()-viewModel.catWidth.toInt())) {
                    viewModel.catX+=2
                    delay(2)
                }
                enable1 = true
            }
        }
    }
}

@Composable
fun Cat(viewModel: MainActivityView) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
//        val catXAnim by animateDpAsState(targetValue = viewModel.catX.dp, animationSpec = tween(400))
        Image(
            painter = painterResource(viewModel.painterId),
            contentDescription = "cat",
            modifier = Modifier
                .padding(start = viewModel.catX.dp)
//                .background(Color.Cyan)
                .size(width = viewModel.catWidth.dp, height = viewModel.catHeight.dp)
                .align(Alignment.BottomStart)
        )
    }
}

@Composable
fun CountAndHP(viewModel: MainActivityView, viewFish: FishViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 5.dp, top = 5.dp),
        horizontalAlignment =Alignment.Start,
        verticalArrangement =Arrangement.Top
    ) {
        Text(
            text = "Time: ${viewModel.time}",
            fontSize = (viewModel.dpHeight/20).sp,
            fontWeight = FontWeight.W700
        )
        Text(
            text = "Score: ${viewFish.score}",
            fontSize = (viewModel.dpHeight/20).sp,
            fontWeight = FontWeight.W700
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Top
    ) {
        HP(viewModel)
    }
}

@Composable
fun HP(viewModel: MainActivityView) {
    Row(modifier = Modifier
        .padding(end = 15.dp)) {
        OneHP(viewModel = viewModel, 2)
        OneHP(viewModel = viewModel, 1)
        OneHP(viewModel = viewModel, 0)
    }
}

@Composable
fun OneHP(viewModel: MainActivityView, id: Int){
    Image(
        painter = painterResource(id = R.drawable.heart),
        contentDescription = "1HP",
        modifier = Modifier
            .alpha(viewModel.HPState[id])
            .size((viewModel.dpHeight / 10).dp)
            .padding(top = 5.dp, end = 1.dp)
    )
}

@Composable
fun Fish(viewFish:FishViewModel, id:Int){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = viewFish.fishList[id].painter),
            contentDescription = "fish$id",
            modifier = Modifier
                .absolutePadding(
                    bottom = viewFish.fishFall[id].dp,
                    left = viewFish.paddingList[id].dp
                )
                .size(width = viewFish.fishWidth.dp, height = viewFish.fishHight.dp)
                .align(Alignment.BottomStart),
            //                .padding( start = viewFish.paddingList.value[id].dp)

        )
    }
}

@Composable
fun FishBand(viewFish: FishViewModel){
    Fish(viewFish = viewFish, id = 0)
    Fish(viewFish = viewFish, id = 1)
    Fish(viewFish = viewFish, id = 2)
    Fish(viewFish = viewFish, id = 3)
    Fish(viewFish = viewFish, id = 4)
}

@Composable
fun BackgroundImage() {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "back",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}
