package net.rafgpereira.coinwatcher.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.rafgpereira.coinwatcher.ui.common.ThemedTopAppBar

@Composable
fun MainScreen(modifier: Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { ThemedTopAppBar(modifier, "CoinWatcher") },
        content = { padding ->
            Column(
                modifier = modifier
                    .padding(padding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Hello, Watcher")
            }
        },
    )
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun MainScreenPreview() = MainScreen(Modifier)