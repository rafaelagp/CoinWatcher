package net.rafgpereira.coinwatcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import net.rafgpereira.coinwatcher.ui.navigation.MyNavHost
import net.rafgpereira.coinwatcher.ui.theme.CoinWatcherTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinWatcherTheme {
                MyNavHost(rememberNavController())
            }
        }
    }
}