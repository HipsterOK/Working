package ru.porcupine.economyquiz.elements

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.porcupine.economyquiz.R
import ru.porcupine.economyquiz.controller.MainController
import ru.porcupine.economyquiz.functions.NewIntents

class ResultElements(context: Context) {

    private val mainController = MainController(context)

    @Composable
    fun PlayButton(context:Context){
        Elements().TemplateButton(
            mainController = mainController,
            icon = R.drawable.play,
            size = 6
        ) {
            NewIntents().newIntentGame(context)
        }
    }

    @Composable
    fun HomeButton(context: Context){
        Elements().TemplateButton(
            mainController = mainController,
            icon = R.drawable.home,
            size = 6
        ) {
            NewIntents().newIntentHome(context)
        }
    }

    @Composable
    fun Score(score: Int){
        Elements().TemplateText(
            mainController = mainController,
            text = "Score: $score",
            h = 14f,
            w = 3f
        )
    }

    @Composable
    fun BestRecord(record: Int){
        Elements().TemplateText(
            mainController = mainController,
            text = "Record: $record",
            h = 14f,
            w = 3f
        )
    }

    @Composable
    fun Character(){
        Image(
            painter = painterResource(id = R.drawable.character),
            contentDescription = "character",
            alignment = Alignment.BottomCenter,
            modifier = Modifier
                .size(
                    height = (mainController.screenHeight/2.3).dp,
                    width = (mainController.screenWidth).dp
                )
        )
    }
}