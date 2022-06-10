package com.slc.wordme.domain.usecase

import com.slc.wordme.data.GamesRepository
import com.slc.wordme.domain.model.History
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val repository: GamesRepository
) {

    suspend operator fun invoke(type: Int): List<History> = repository.getHistory(type)

}