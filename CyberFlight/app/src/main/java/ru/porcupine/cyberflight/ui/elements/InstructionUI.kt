package ru.porcupine.cyberflight.ui.elements

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.porcupine.cyberflight.R
import ru.porcupine.cyberflight.controllers.GameController
import ru.porcupine.cyberflight.controllers.InstructionController

class InstructionUI {
    @Composable
    fun Instruction(context: Context, gameController: GameController, instructionController: InstructionController) {
        Box(
            Modifier
                .fillMaxSize()
                .alpha(instructionController.alpha)){
            Default().Background(context)
            Box(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxSize().padding(gameController.const.padding.dp)) {
                Default().Button(
                    gameController = gameController,
                    text = "Home",
                    h = 14f,
                    w = 3f,
                    font = 20f
                ) {
                    instructionController.alpha = 0f
                }
            }

            Text(
                text = "Welcome to Cyber flight!\n" +
                        "You are flying an airplane, but a meteor shower has started in your world.\n" +
                        "Your goal is to hold out as long as possible, dodging falling meteorites.\n" +
                        "To move to the left - click on the left half of the screen, to move to the right - right.\n" +
                        "Good luck!",
                textAlign = TextAlign.Center,
                fontSize = (gameController.const.screenHeight/35).sp,
                color = colorResource(id = R.color.red),
                style = TextStyle(fontFamily = FontFamily(Font(gameController.const.font))),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding((gameController.const.padding*2).dp)
//                    .background(Color.White)
            )
        }
    }
}