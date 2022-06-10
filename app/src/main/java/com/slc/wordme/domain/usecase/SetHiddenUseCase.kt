package com.slc.wordme.domain.usecase

import com.slc.wordme.data.GamesRepository
import javax.inject.Inject

class SetHiddenUseCase @Inject constructor(
    private val repository: GamesRepository
) {

    suspend operator fun invoke(id: Int, visible: Boolean) {
        repository.setHidden(id, visible)
    }

}