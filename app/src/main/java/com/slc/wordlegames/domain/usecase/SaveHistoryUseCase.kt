package com.slc.wordlegames.domain.usecase

import com.slc.wordlegames.data.GamesRepository
import com.slc.wordlegames.domain.model.History
import javax.inject.Inject

class SaveHistoryUseCase @Inject constructor(
    private val repository: GamesRepository
) {

    suspend operator fun invoke(history: History) {
        repository.saveHistory(history)
    }

}