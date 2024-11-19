package net.rafgpereira.coinwatcher.data.local.db.model

object FakeWatchedCoin {
    val fake0 = WatchedCoin(0, "0", "Bitcoin", null)
    val fake1 = WatchedCoin(1, "1", "Ethereum", null)
    val fake2 = WatchedCoin(2, "2", "Polygon", null)
    val fakes = arrayOf(fake0, fake1, fake2)
}