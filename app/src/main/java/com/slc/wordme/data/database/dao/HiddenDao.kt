package com.slc.wordme.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.slc.wordme.data.database.entity.HiddenEntity
import com.slc.wordme.data.database.entity.HistoryEntity

@Dao
interface HiddenDao {

    @Query("SELECT * FROM hidden WHERE hidden.visible == 0")
    suspend fun getHidden(): List<HiddenEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHidden(hidden: HiddenEntity)

}