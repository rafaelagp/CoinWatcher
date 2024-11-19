package net.rafgpereira.coinwatcher.data.local.db.repository

import net.rafgpereira.coinwatcher.data.local.db.dao.IWatchedCoinDao
import net.rafgpereira.coinwatcher.data.local.db.model.FakeWatchedCoin
import net.rafgpereira.coinwatcher.data.local.db.model.WatchedCoin
import javax.inject.Inject

class WatchedCoinRepository @Inject constructor(
    private val watchedCoinDao: IWatchedCoinDao,
) {
    // TODO replace with actual db call
    fun getAll() = FakeWatchedCoin.fakes //watchedCoinDao.getAll()
    fun insert(vararg items: WatchedCoin) = watchedCoinDao.insert(*items)
    fun delete(item: WatchedCoin) = watchedCoinDao.delete(item)
}