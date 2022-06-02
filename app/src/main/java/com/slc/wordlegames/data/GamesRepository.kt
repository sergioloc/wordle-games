package com.slc.wordlegames.data

import com.slc.wordlegames.domain.model.Game
import javax.inject.Inject

class GamesRepository @Inject constructor() {

    fun getGames(): List<Game> {
        return listOf(
            Game(name = "Wordle", url = "https://wordle.danielfrg.com/"),
            Game(name = "Worldle", url = "https://worldle.teuteuf.fr/"),
            Game(name = "Heardle", url = "https://www.heardle.app/"),
            Game(name = "Framed", url = "https://framed.wtf/")
        )
    }

}