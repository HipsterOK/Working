package ru.porcupine.hitthegoal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = MainActivityView()

            viewModel.startTimer(this@MainActivity)
            viewModel.moveGates()

        setContent {
           MainContent(viewModel)
        }
    }
}

@Composable
fun MainContent(viewModel: MainActivityView) {
    Background()
    Gates(viewModel)
    Goal(viewModel)
    Ball(viewModel)
    Control(viewModel)
}

@Composable
fun Gates(viewModel: MainActivityView) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.gates),
            contentDescription = "gates",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .absolutePadding(left = (viewModel.positionGateX).dp)
                .size(width = viewModel.gateWidth.dp, height = viewModel.gateHeight.dp)
                .align(Alignment.TopStart)
//                .background(Color.Gray),
        )
    }
}

@Composable
fun Ball(viewModel: MainActivityView) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ball),
            contentDescription = "ball",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .absolutePadding(bottom = (viewModel.positionBallY).dp)
                .size(viewModel.ballSize.dp)
                .align(Alignment.BottomCenter)
//                .background(Color.Gray),
        )
    }
}

@Composable
fun Control(viewModel: MainActivityView) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter),
//                .background(Color.Gray, Shapes.medium),
            onClick = {
                viewModel.startBall()
            },
            enabled = viewModel.butEnbl
        ) {
            Text(text = "HIT")
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 15.dp)
        ) {
            Text(
                text = "Score: ${viewModel.score}",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 5.dp),
//                    .background(Color.Gray, Shapes.medium),
                fontWeight = FontWeight(700),
                fontSize = 16.sp
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 15.dp)
        ) {
            Text(
                text = "Time: ${viewModel.timer}",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 5.dp),
//                    .background(Color.Gray, Shapes.medium),
                fontWeight = FontWeight(700),
                fontSize = 15.sp
            )
        }

    }
}

@Composable
fun Goal(viewModel: MainActivityView) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.goal),
            contentDescription = "goal",
            contentScale = ContentScale.Inside,
            modifier = Modifier
//                .size(viewModel.ballSize.dp)
                .align(Alignment.Center)
                .alpha(viewModel.viewGoal)
//                .background(Color.Gray),
        )
    }
}

@Composable
fun Background(){
    Image(
        painter = painterResource(id = R.drawable.background_main),
        contentDescription = "back",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}