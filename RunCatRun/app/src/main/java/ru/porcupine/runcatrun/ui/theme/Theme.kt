package ru.porcupine.runcatrun.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)
val Colors.backColor: Color
    get() = if (isLight) Color(0xFF0078C9) else Color(0xFF0081D6)

val Colors.earthColor: Color
    get() = if (isLight) Color(0xFF1A9100) else Color(0xFF00A509)

val Colors.cloudColor: Color
    get() = if (isLight) Color(0xFFDBDBDB) else Color(0xFFACACAC)

val Colors.playerColor: Color
    get() = if (isLight) Color(0xFF535353) else Color(0xFFACACAC)

val Colors.enemyColor: Color
    get() = if (isLight) Color(0xFF535353) else Color(0xFFACACAC)

val Colors.gameOverColor: Color
    get() = if (isLight) Color(0xFF000000) else Color(0xFFFFFFFF)

val Colors.currentScoreColor: Color
    get() = if (isLight) Color(0xFF535353) else Color(0xFFACACAC)

val Colors.highScoreColor: Color
    get() = if (isLight) Color(0xFF757575) else Color(0xFF909191)

@Composable
fun RunCatRunTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}