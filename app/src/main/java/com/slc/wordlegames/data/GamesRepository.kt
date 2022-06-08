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

    fun getGlobalGames(): List<Game> {
        return listOf(
            Game(id = 10, name = "Wordle", image = R.drawable.ic_keyboard, url = "https://www.nytimes.com/games/wordle/index.html"),
            Game(id = 11, name = "Worldle", image = R.drawable.ic_world, url = "https://worldle.teuteuf.fr/"),
            Game(id = 12, name = "Flaggle", image = R.drawable.ic_flag, url = "https://ducc.pythonanywhere.com/flaggle/"),
            Game(id = 13, name = "Mathler", image = R.drawable.ic_math, url = "https://www.mathler.com/"),
            Game(id = 14, name = "Nerdle", image = R.drawable.ic_function, url = "https://nerdlegame.com/"),
            Game(id = 15, name = "Poeltl", image = R.drawable.ic_basketball, url = "https://poeltl.dunk.town/"),
        )
    }

    fun getSpanishGames(): List<Game> {
        return listOf(
            Game(id = 1, name = "Wordle (ES)", image = R.drawable.ic_keyboard, url = "https://wordle.danielfrg.com/"),
            Game(id = 2, name = "Tildes", image = R.drawable.ic_tilde, url = "https://wordle.danielfrg.com/tildes/index.html"),
            Game(id = 3, name = "Científico", image = R.drawable.ic_science, url = "https://wordle.danielfrg.com/ciencia/index.html"),
            Game(id = 4, name = "Contrareloj", image = R.drawable.ic_science, url = "https://wordle.danielfrg.com/contrarreloj/index.html"),
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