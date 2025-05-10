package net.rafgpereira.coinwatcher.data

import net.rafgpereira.coinwatcher.ui.screen.main.Coin

val chartData = listOf(
    Pair(6, 111.45),
    Pair(7, 111.0),
    Pair(8, 113.45),
    Pair(9, 112.25),
    Pair(10, 116.45),
    Pair(11, 113.35),
    Pair(12, 118.65),
    Pair(13, 110.15),
    Pair(14, 113.05),
    Pair(15, 114.25),
    Pair(16, 116.35),
    Pair(17, 117.45),
    Pair(18, 112.65),
    Pair(19, 115.45),
    Pair(20, 111.85)
)

val coins = listOf(
    Coin("BTC", "Bitcoin", 101000.00, 10.0, chartData),
    Coin("ETH", "Ethereum", 2150.00, 6.5, chartData),
    Coin("DOGE", "DogeCoin", 0.00004346, -11.2, chartData),
    Coin("BNB", "Binance Network", 35.346, 2.3, chartData),
    Coin("ETH", "Ethereum", 2150.00, 6.5, chartData),
    Coin("DOGE", "DogeCoin", 0.00004346, -11.2, chartData),
)