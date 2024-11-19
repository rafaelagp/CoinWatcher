package net.rafgpereira.coinwatcher.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import net.rafgpereira.coinwatcher.data.local.db.model.WatchedCoin

@Dao
interface WatchedCoinDao {
    @Query("SELECT * FROM watchedcoin")
    fun getAll(): List<WatchedCoin>
    @Insert
    fun insert(vararg items: WatchedCoin)
    @Delete
    fun delete(item: WatchedCoin)
}