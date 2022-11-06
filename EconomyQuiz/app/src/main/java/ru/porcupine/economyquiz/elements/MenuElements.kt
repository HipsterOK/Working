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
import ru.porcupine.economyquiz.functions.NewIntents
import ru.porcupine.economyquiz.R
import ru.porcupine.economyquiz.controller.MainController

class MenuElements(context: Context) {

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
    fun InfoButton(context: Context){
        Elements().TemplateButton(
            mainController = mainController,
            icon = R.drawable.info,
            size = 6
        ) {
            NewIntents().newIntentInfo(context)
        }
    }

    @Composable
    fun Policy(context: Context){
        Elements().TemplateButton(
            mainController = mainController,
            icon = R.drawable.privacy,
            size = 6
        ) {
            val url = context.getString(R.string.site)
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(url))
        }
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

    @Composable
    fun Money(){
        Image(
            painter = painterResource(id = R.drawable.gold),
            contentDescription = "money",
            alignment = Alignment.BottomStart,
            modifier = Modifier
                .size(
                    height = (mainController.screenHeight/2.3).dp,
                    width = (mainController.screenWidth).dp
                )
        )
    }
}