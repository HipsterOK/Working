package ru.porcupine.runcatrun

import android.graphics.Matrix
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.vector.PathParser


const val BASE_SCALE = 0.75f
const val DOUBT_FACTOR = 20f
val PLAYER_HEIGHT = (deviceHeightInPixels/7).toFloat()
val PLAYER_WIDTH = (deviceWidthInPixels/5).toFloat()

val ENEMY_HEIGHT = (deviceHeightInPixels/9).toFloat()
val ENEMY_WIDTH = (deviceWidthInPixels/6).toFloat()

private var CLOUD_PATH_STR = "M169.895,88.699C167.27,71.887 152.695,58.984 135.156,58.984C128.559,58.984 122.219,60.809 116.719,64.215C108.355,50.156 93.293,41.406 76.563,41.406C50.715,41.406 29.688,62.434 29.688,88.281C29.688,88.441 29.688,88.609 29.691,88.766C13.082,91.566 0,106.047 0,123.438C0,142.824 16.16,158.594 35.547,158.594L164.453,158.594C183.84,158.594 200,142.824 200,123.438C200,105.898 186.707,91.324 169.895,88.699ZM169.895,88.699"
private var cloudPath = PathParser().parsePathString(CLOUD_PATH_STR)
fun cloudPath(): Path {
    val path = cloudPath.toPath()
    val scaleMatrix = Matrix()
    scaleMatrix.setScale(BASE_SCALE, BASE_SCALE, 0f, 0f)
    val androidPath = path.asAndroidPath()
    androidPath.transform(scaleMatrix)
    return androidPath.asComposePath()
}