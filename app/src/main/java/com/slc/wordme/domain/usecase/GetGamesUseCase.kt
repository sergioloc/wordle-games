package com.slc.wordme.domain.usecase

import com.slc.wordme.data.GamesRepository
import com.slc.wordme.domain.model.Game
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class GetGamesUseCase @Inject constructor(
    private val repository: GamesRepository
) {

    operator fun invoke(): List<Game> {
        val result = ArrayList<Game>()

        val isSpanish = Locale.getDefault().language == "es"

        if (isSpanish)
            result.addAll(repository.getSpanishGames())
        result.addAll(repository.getGlobalGames())

        if (isSpanish)
            result[4].name = "Wordle (EN)"

        return result
    }

}