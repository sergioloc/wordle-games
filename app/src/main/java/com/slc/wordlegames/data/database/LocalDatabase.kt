package com.slc.wordlegames.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.slc.wordlegames.data.database.dao.HistoryDao
import com.slc.wordlegames.data.database.entity.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun getHistoryDao(): HistoryDao

}