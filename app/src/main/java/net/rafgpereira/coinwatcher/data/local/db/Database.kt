package net.rafgpereira.coinwatcher.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import net.rafgpereira.coinwatcher.data.local.db.dao.WatchedCoinDao
import net.rafgpereira.coinwatcher.data.local.db.model.WatchedCoin

@Database(
    entities = [ WatchedCoin::class ],
    version = 1,
)
abstract class Database: RoomDatabase() {
    abstract fun WatchedCoinDao(): WatchedCoinDao
}