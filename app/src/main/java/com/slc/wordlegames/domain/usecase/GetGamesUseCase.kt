package com.slc.wordlegames.domain.usecase

import com.slc.wordlegames.data.GamesRepository
import com.slc.wordlegames.domain.model.Game
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GamesRepository
) {

    operator fun invoke(): List<Game> = repository.getGames()

}