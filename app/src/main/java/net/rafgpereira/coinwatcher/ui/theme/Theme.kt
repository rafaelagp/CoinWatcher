package net.rafgpereira.coinwatcher.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ColorScheme.positive: Color
    @Composable
    get() = if (isSystemInDarkTheme()) FluorescentGreen else Color.Yellow

val ColorScheme.negative: Color
    @Composable
    get() = if (isSystemInDarkTheme()) BrightRed else Color.Red

private val DarkColorScheme = darkColorScheme(
    primary = DarkPurple,
    secondary = FluorescentGreen,
    tertiary = Pink80,
    onPrimary = FluorescentGreen,
    background = DarkGrey,
    primaryContainer = MidGrey,
)

private val LightColorScheme = lightColorScheme(
    primary = LightBlue,
    secondary = LightRed,
    tertiary = Pink40,
    onPrimary = LightRed,
    background = LightGrey,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CoinWatcherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}