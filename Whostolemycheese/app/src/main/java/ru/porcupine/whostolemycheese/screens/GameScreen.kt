package ru.porcupine.whostolemycheese.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.porcupine.whostolemycheese.utils.Controller
import ru.porcupine.whostolemycheese.R

class GameScreen {
    @Composable
    fun GameContent(controller: Controller) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(controller.screensController.gamePos.dp, 0.dp)
                .alpha(controller.screensController.gameAlpha)
        ) {
            MenuScreen().Back()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Score(controller)
                Enemy(controller)
                Player(controller)
            }
            Controller(controller)
        }
    }

    @Composable
    fun Score(controller: Controller){
        Text(
            text = "Score: ${controller.variables.score}",
            textAlign = TextAlign.Center,
            color = Color.Black,
            style = TextStyle(fontFamily = FontFamily(Font(controller.constants.font))),
            modifier = Modifier
        )
    }

    @Composable
    fun Enemy(controller: Controller){
        val posX by animateDpAsState(targetValue = controller.enemy.posX.value.dp)
        Image(
            painter = painterResource(id = R.drawable.cat),
            contentDescription = "",
            modifier = Modifier
                .size(controller.constants.enemySize.dp)
                .offset(posX, 0.dp)
        )
    }

    @Composable
    fun Player(controller: Controller){
        Image(
            painter = painterResource(id = controller.player.image.value),
            contentDescription = "",
            modifier = Modifier
                .size(controller.constants.playerSize.dp)
        )
    }

    @Composable
    fun Controller(controller: Controller) {
        Box(
            Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            try {
                                controller.player.changeStateToUnsave()
                                awaitRelease()
                            } finally {
                                controller.player.changeStateToSave()
                            }
                        },
                    )
                }
                .alpha(0f)
        )
    }
}