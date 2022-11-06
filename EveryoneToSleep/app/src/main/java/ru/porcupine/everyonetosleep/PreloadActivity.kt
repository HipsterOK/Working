package ru.porcupine.everyonetosleep

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import ru.porcupine.everyonetosleep.activities.MenuActivity
import java.util.*
import kotlin.concurrent.timerTask

class PreloadActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timer().schedule(timerTask {
            val gameIntent = Intent(this@PreloadActivity, MenuActivity::class.java)
            startActivity(gameIntent)
            finish()
        }, 3000)


        setContent {
            Box(
                Modifier.fillMaxSize().background(Color.Black)
            )
            Box(Modifier.fillMaxSize()) {
                val imageLoader = ImageLoader.Builder(this@PreloadActivity)
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
                        model = R.drawable.loading,
                        imageLoader = imageLoader,
                    ),
                    "loader",
                    modifier = Modifier
                        .fillMaxSize()
                        .size((Logic(this@PreloadActivity).screenWidth/5).dp),
                    contentScale = ContentScale.Inside,
                )
            }
        }
    }
}