package ru.porcupine.onecircletwocircle.graphics

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.porcupine.onecircletwocircle.R
import ru.porcupine.onecircletwocircle.controller.GameController
import ru.porcupine.onecircletwocircle.controller.InfoController
import ru.porcupine.onecircletwocircle.functions.ChangeActivity

class HomeUI(context: Context) {
    private val gameController = GameController(context)

    @Composable
    fun Play(context: Context) {
        Template().Button(
            gameController = gameController,
            text = "Play",
            h = 14f,
            w = 3f,
            font = 20f
        ) {
            ChangeActivity().newIntentGame(context)
        }
    }

    @Composable
    fun Info(infoController: InfoController) {
        Template().Button(
            gameController = gameController,
            text = "Info",
            h = 14f,
            w = 3f,
            font = 20f
        ) {
            infoController.alpha=1f
        }
    }

    @Composable
    fun Policy(context: Context) {
        Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.fillMaxSize().padding(gameController.padding.dp)) {
            Template().Button(
                gameController = gameController,
                text = "Privacy \n policy",
                h = 10f,
                w = 3.5f,
                font = 20f
            ) {
                val url = context.getString(R.string.browser)
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(context, Uri.parse(url))
            }
        }
    }

    @Composable
    fun BestRecord(record: Int) {
        Template().TemplateText(
            gameController = gameController,
            text = "Record: $record",
            h = 14f,
            w = 3f,
            font = 20f
        )
    }

}
