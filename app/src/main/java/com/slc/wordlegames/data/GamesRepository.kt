package com.slc.wordlegames.data

import com.slc.wordlegames.R
import com.slc.wordlegames.data.database.dao.HiddenDao
import com.slc.wordlegames.data.database.dao.HistoryDao
import com.slc.wordlegames.data.database.entity.HiddenEntity
import com.slc.wordlegames.domain.model.*
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val historyDao: HistoryDao,
    private val hiddenDao: HiddenDao
) {

    fun getGames(): List<Game> {
        return listOf(
            Game(id = 1, name = "Wordle", image = R.drawable.ic_keyboard, url = "https://wordle.danielfrg.com/"),
            Game(id = 2, name = "Worldle", image = R.drawable.ic_world, url = "https://worldle.teuteuf.fr/"),
            Game(id = 3, name = "Heardle", image = R.drawable.ic_music, url = "https://www.heardle.app/"),
            Game(id = 4, name = "Framed", image = R.drawable.ic_movie, url = "https://framed.wtf/")
        )
    }

    /** History DAO **/

    suspend fun getHistory(type: Int): List<History> {
        val response = historyDao.getHistory(type)
        return response.map { it.toDomain() }
    }

    suspend fun getCurrentResult(type: Int, date: String): List<History> {
        val response = historyDao.getCurrentResult(type, date)
        return response.map { it.toDomain() }
    }

    suspend fun saveHistory(history: History) {
        historyDao.insertHistory(history.toData())
    }

    /** Hidden DAO **/

    suspend fun getHidden(): List<Int> {
        val response = hiddenDao.getHidden()
        val result = ArrayList<Int>()
         for (item in response)
             result.add(item.id)
        return result
    }

    suspend fun setHidden(id: Int, visible: Boolean) {
        hiddenDao.insertHidden(HiddenEntity(id, visible))
    }

}