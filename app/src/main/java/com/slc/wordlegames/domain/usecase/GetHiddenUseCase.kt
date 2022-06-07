package com.slc.wordlegames.domain.usecase

import com.slc.wordlegames.data.GamesRepository
import javax.inject.Inject

class GetHiddenUseCase @Inject constructor(
    private val repository: GamesRepository
) {

    suspend operator fun invoke(): List<Int> = repository.getHidden()

}