package com.slc.wordlegames.domain.usecase

import com.slc.wordlegames.data.GamesRepository
import javax.inject.Inject

class IsGamePlayedUseCase @Inject constructor(
    private val repository: GamesRepository
) {

    suspend operator fun invoke(type: Int, date: String): Boolean? {
        val list = repository.getCurrentResult(type, date)

        if (list.isEmpty())
            return null

        return list[0].status
    }

}