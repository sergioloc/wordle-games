package com.slc.wordlegames.domain.model

import com.slc.wordlegames.data.database.entity.HistoryEntity

data class History(
    val type: Int,
    val status: Boolean,
    val result: String,
    val date: String
)

fun HistoryEntity.toDomain() = History(type, status, result, date)

fun History.toData() = HistoryEntity(type = type, status = status, result = result, date = date)