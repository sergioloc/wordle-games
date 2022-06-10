package com.slc.wordme.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.slc.wordme.data.database.dao.HiddenDao
import com.slc.wordme.data.database.dao.HistoryDao
import com.slc.wordme.data.database.entity.HiddenEntity
import com.slc.wordme.data.database.entity.HistoryEntity

@Database(entities = [HistoryEntity::class, HiddenEntity::class], version = 1)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun getHistoryDao(): HistoryDao

    abstract fun getHiddenDao(): HiddenDao

}