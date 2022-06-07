package com.slc.wordlegames.domain.usecase

import com.slc.wordlegames.data.GamesRepository
import com.slc.wordlegames.domain.model.History
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val repository: GamesRepository
) {

    suspend operator fun invoke(type: Int): List<History> = repository.getHistory(type)

}