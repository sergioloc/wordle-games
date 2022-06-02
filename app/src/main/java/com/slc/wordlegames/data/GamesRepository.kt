package com.slc.wordlegames.data

import com.slc.wordlegames.data.model.GameModel

class GamesRepository {

    suspend fun getGames(): List<GameModel> {
        return listOf(
            GameModel(name = "Wordle", url = "https://wordle.danielfrg.com/"),
            GameModel(name = "Worldle", url = "https://worldle.teuteuf.fr/"),
            GameModel(name = "Heardle", url = "https://www.heardle.app/"),
            GameModel(name = "Framed", url = "https://framed.wtf/")
        )
    }

}