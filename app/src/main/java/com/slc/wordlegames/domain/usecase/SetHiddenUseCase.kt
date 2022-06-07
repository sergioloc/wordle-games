package com.slc.wordlegames.domain.usecase

import com.slc.wordlegames.data.GamesRepository
import javax.inject.Inject

class SetHiddenUseCase @Inject constructor(
    private val repository: GamesRepository
) {

    suspend operator fun invoke(id: Int, visible: Boolean) {
        repository.setHidden(id, visible)
    }

}