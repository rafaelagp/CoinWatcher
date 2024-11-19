package net.rafgpereira.coinwatcher.data.local.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WatchedCoin (
    @PrimaryKey val uid: Int,
    val remoteId: String,
    val name: String,
    val iconUrl: String?,
)