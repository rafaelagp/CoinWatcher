package net.rafgpereira.coinwatcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.rafgpereira.coinwatcher.ui.screen.main.MainScreen
import net.rafgpereira.coinwatcher.ui.theme.CoinWatcherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThemedMainScreen()
        }
    }
}

@Composable
fun ThemedMainScreen() =
    CoinWatcherTheme {
        MainScreen(Modifier)
    }

@Composable
@Preview(showSystemUi = true)
fun ThemedMainScreenPreview() = ThemedMainScreen()