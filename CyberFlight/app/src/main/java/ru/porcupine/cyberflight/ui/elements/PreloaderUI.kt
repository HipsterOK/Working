package ru.porcupine.cyberflight.ui.elements

import android.content.Context
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import ru.porcupine.cyberflight.R

class PreloaderUI {
    @Composable
    fun Preloader(context: Context){
        Box(Modifier.fillMaxSize().background(Color.Black)) {
            val imageLoader = ImageLoader.Builder(context = context)
                .components {
                    if (Build.VERSION.SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }
                .build()

            Image(
                painter = rememberAsyncImagePainter(
                    model = R.drawable.preloader,
                    imageLoader = imageLoader
                ),
                "preloader",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Inside,
            )
        }
    }
}