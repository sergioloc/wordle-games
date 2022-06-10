package com.slc.wordme.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "type") val type: Int,
    @ColumnInfo(name = "status") val status: Boolean,
    @ColumnInfo(name = "result") val result: String,
    @ColumnInfo(name = "date") val date: String
)
