package net.rafgpereira.coinwatcher.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.rafgpereira.coinwatcher.R
import net.rafgpereira.coinwatcher.ui.common.ThemedAddFab
import net.rafgpereira.coinwatcher.ui.common.ThemedTopAppBar

@Composable
fun MainScreen(
    modifier: Modifier,
    onClickFab: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { ThemedTopAppBar(modifier, stringResource(R.string.app_name)) },
        floatingActionButton = { ThemedAddFab(modifier, onClickFab) },
        floatingActionButtonPosition = FabPosition.End,
        content = { innerPadding ->
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) { }
        },
    )
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun MainScreenPreview() = MainScreen(Modifier) {}