package com.slc.wordlegames.data

import com.slc.wordlegames.R
import com.slc.wordlegames.data.database.dao.HistoryDao
import com.slc.wordlegames.domain.model.Game
import com.slc.wordlegames.domain.model.History
import com.slc.wordlegames.domain.model.toData
import com.slc.wordlegames.domain.model.toDomain
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val historyDao: HistoryDao
) {

    fun getGames(): List<Game> {
        return listOf(
            Game(id = 1, name = "Wordle", image = R.drawable.ic_keyboard, url = "https://wordle.danielfrg.com/"),
            Game(id = 2, name = "Worldle", image = R.drawable.ic_world, url = "https://worldle.teuteuf.fr/"),
            Game(id = 3, name = "Heardle", image = R.drawable.ic_music, url = "https://www.heardle.app/"),
            Game(id = 4, name = "Framed", image = R.drawable.ic_movie, url = "https://framed.wtf/")
        )
    }

    suspend fun getHistory(type: Int): List<History> {
        val response = historyDao.getHistory(type)
        return response.map { it.toDomain() }
    }

    suspend fun saveHistory(history: History) {
        historyDao.insertHistory(history.toData())
    }

}