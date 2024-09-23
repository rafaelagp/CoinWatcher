package net.rafgpereira.coinwatcher.ui.common

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.rafgpereira.coinwatcher.ui.theme.CoinWatcherTheme

@Composable
fun ThemedFab(
    modifier: Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) = FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        content = content,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        shape = CircleShape,
    )

@Composable
fun ThemedAddFab(modifier: Modifier, onClick: () -> Unit,) =
    ThemedFab(
        modifier = modifier,
        onClick = onClick,
        content = {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    )

@Composable
@Preview
fun ThemedFabPreview() =
    CoinWatcherTheme {
        ThemedAddFab(
            modifier = Modifier,
            onClick = {}
        )
    }

