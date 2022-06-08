package com.slc.wordlegames.domain.usecase

import com.slc.wordlegames.data.GamesRepository
import com.slc.wordlegames.domain.model.Game
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GamesRepository
) {

    operator fun invoke(): List<Game> {
        val result = ArrayList<Game>()

        var isSpanish = true

        if (isSpanish)
            result.addAll(repository.getSpanishGames())
        result.addAll(repository.getGlobalGames())

        if (isSpanish)
            result[3].name = "Wordle (EN)"

        return result
    }

}