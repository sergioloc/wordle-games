package com.slc.wordlegames.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.slc.wordlegames.data.database.entity.HistoryEntity

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history WHERE history.type == :type")
    suspend fun getHistory(type: Int): List<HistoryEntity>

    @Query("SELECT * FROM history WHERE history.type == :type AND history.date == :date")
    suspend fun getCurrentResult(type: Int, date: String): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: HistoryEntity)

}