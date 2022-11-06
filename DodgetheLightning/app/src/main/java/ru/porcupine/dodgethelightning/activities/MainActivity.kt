package ru.porcupine.dodgethelightning.activities

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import ru.porcupine.dodgethelightning.Controller
import ru.porcupine.dodgethelightning.R
import ru.porcupine.dodgethelightning.Templates

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val controller = Controller(this)
        controller.startGame(lifecycleScope, this)
        setContent {
            MainContent(controller)
        }
    }

    @Composable
    fun MainContent(controller: Controller) {

        val posXDog by animateDpAsState(targetValue = controller.dogX.dp)
        Templates().Background(R.drawable.game_back)
            Templates().TemplateTextField(
                controller = controller,
                text = "SCORE: ${controller.score}",
                h = 11,
                w = 3.0
            )
            Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.tree),
                contentDescription = "tree",
                alignment = Alignment.BottomEnd,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        bottom = (controller.screenHeight / 6).dp,
                        start = (controller.screenWidth / 3).dp
                    )
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.dog),
                contentDescription = "dog",
                alignment = Alignment.BottomStart,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(
                        bottom = (controller.screenHeight / 6).dp,
                        start = (controller.screenWidth / 8).dp
                    )
                    .size(controller.playerSize.dp)
                    .absoluteOffset(x = posXDog, y = 0.dp)
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = controller.cloudState),
                contentDescription = "cloud",
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = (controller.screenHeight / 6).dp)
                    .size(controller.cloudSize.dp)
            )
        }

        val imageLoader = ImageLoader.Builder(this)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

        Image(
            painter = rememberAsyncImagePainter(
                model = R.drawable.rain,
                imageLoader = imageLoader
            ),
            "rain",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(controller.alphaFlash)
                .background(color = Color.White)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0f)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = {
                        if(controller.dogX < controller.posXTree/2) {
                            controller.dogX = controller.posXTree
                        } else controller.dogX = 0
                    }
                )
        )

    }
}