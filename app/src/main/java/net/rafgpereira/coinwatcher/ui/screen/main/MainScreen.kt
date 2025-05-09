package net.rafgpereira.coinwatcher.ui.screen.main

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import net.rafgpereira.coinwatcher.R
import net.rafgpereira.coinwatcher.data.coins
import net.rafgpereira.coinwatcher.ui.common.PillText
import net.rafgpereira.coinwatcher.ui.common.ThemedAddFab
import net.rafgpereira.coinwatcher.ui.common.ThemedTopAppBar
import net.rafgpereira.coinwatcher.ui.theme.CoinWatcherTheme
import net.rafgpereira.coinwatcher.ui.theme.MidGrey
import net.rafgpereira.coinwatcher.ui.theme.negative
import net.rafgpereira.coinwatcher.ui.theme.positive

@Composable
fun MainScreen(
    modifier: Modifier,
    coins: List<Coin> = net.rafgpereira.coinwatcher.data.coins
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { ThemedTopAppBar(modifier, stringResource(R.string.app_name)) },
        floatingActionButton = { ThemedAddFab(modifier) {} },
        floatingActionButtonPosition = FabPosition.End,
        content = { innerPadding ->
            val startPadding = innerPadding.calculateStartPadding(LayoutDirection.Ltr)
            val endPadding = innerPadding.calculateEndPadding(LayoutDirection.Ltr)
            val topPadding = innerPadding.calculateTopPadding() - 40.dp
            val bottomPadding = innerPadding.calculateBottomPadding()

            Column(
                modifier = modifier
                    .padding(
                        top = topPadding,
                        start = startPadding,
                        end = endPadding,
                        bottom = bottomPadding
                    )
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(modifier)
                OrderBar(modifier.padding(top = 2.dp))
                CoinList(modifier, coins)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier) =
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                modifier = modifier,
                query = "",
                onQueryChange = {},
                onSearch = {},
                expanded = false,
                onExpandedChange = {},
                placeholder = { Text(stringResource(R.string.main_search_placeholder)) },
                leadingIcon = { Icon(Icons.Filled.Search, null) },
                colors = SearchBarDefaults.inputFieldColors(
                    cursorColor = MaterialTheme.colorScheme.secondary,
                )
            )
        },
        modifier = modifier,
        expanded = false,
        onExpandedChange = {},
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
    ) {}

@Composable
fun OrderBar(modifier: Modifier) =
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            onClick = {},
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            content = {
                Row {
                    Text(stringResource(R.string.main_order_ticker))
                    Icon(Icons.Filled.KeyboardArrowUp, null)
                }
            },
        )
        TextButton(
            onClick = {},
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            content = { Text(stringResource(R.string.main_order_all_time)) },
        )
        TextButton(
            onClick = {},
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            content = {
                Row {
                    Text(stringResource(R.string.main_order_price))
                    Icon(Icons.Filled.KeyboardArrowDown, null)
                }
            },
        )
    }

@Composable
fun CoinList(modifier: Modifier, data: List<Coin>) =
    LazyColumn(modifier = modifier.fillMaxHeight(),) {
        items(data) {
            Column {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                    ) {
                        PillText(
                            modifier = modifier,
                            text = it.ticker,
                            backgroundColor = MaterialTheme.colorScheme.primary,
                            textColor = Color.White, //TODO change color
                            textStyle = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray, //TODO change color
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        val valueColor =
                            if (it.percentage > 0) MaterialTheme.colorScheme.positive
                            else MaterialTheme.colorScheme.negative

                        PillText(
                            modifier = modifier,
                            text = "$${it.price}",
                            backgroundColor = valueColor,
                            textColor = Color.Black,
                            textStyle = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = "${if (it.percentage > 0) "+" else ""}${it.percentage}%",
                            style = MaterialTheme.typography.labelSmall,
                            color = valueColor,
                        )
                    }
                }
                Box(
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .height(1.dp)
                        .background(MidGrey)
                )
            }
        }
    }

data class Coin(val ticker: String, val name: String, val price: Double, val percentage: Double)

@Composable
@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DarkModeMainScreenPreview() =
    CoinWatcherTheme(content = { MainScreen(Modifier, coins) })

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun LightModeMainScreenPreview() =
    CoinWatcherTheme(content = { MainScreen(Modifier, coins) })
