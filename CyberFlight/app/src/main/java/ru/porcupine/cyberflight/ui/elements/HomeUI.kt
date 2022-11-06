package ru.porcupine.cyberflight.ui.elements

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.porcupine.cyberflight.R
import ru.porcupine.cyberflight.controllers.ActivityController
import ru.porcupine.cyberflight.controllers.GameController
import ru.porcupine.cyberflight.controllers.InstructionController

class HomeUI(context: Context) {
    private val gameController = GameController(context)

    @Composable
    fun PlayerPlane(gameController: GameController){
            Image(
                painter = painterResource(id = R.drawable.airplane),
                contentDescription = "plane",
                modifier = Modifier
                    .size(
                        width = (gameController.const.widthPlane*3).dp,
                        height = (gameController.const.heightPlane*3).dp
                    )
            )
    }

    @Composable
    fun ButtonPlay(context: Context) {
        Default().Button(
            gameController = gameController,
            text = "Play",
            h = 14f,
            w = 3f,
            font = 20f
        ) {
            ActivityController().activityMain(context)
        }
    }

    @Composable
    fun ButtonInstruction(instructionController: InstructionController) {
        Default().Button(
            gameController = gameController,
            text = "Info",
            h = 14f,
            w = 3f,
            font = 20f
        ) {
            instructionController.alpha=1f
        }
    }

    @Composable
    fun ButtonPolicy(context: Context) {
        Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.fillMaxSize().padding(gameController.const.padding.dp)) {
            Default().Button(
                gameController = gameController,
                text = "Privacy \n policy",
                h = 10f,
                w = 3.5f,
                font = 28f
            ) {
                val url = context.getString(R.string.privacy_url)
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(context, Uri.parse(url))
            }
        }
    }

    @Composable
    fun TextRecord(record: Int) {
        Default().TemplateText(
            gameController = gameController,
            text = "Record: $record",
            h = 14f,
            w = 2f,
            font = 20f
        )
    }

}
