package com.slc.wordme.domain.usecase

import com.slc.wordme.data.GamesRepository
import javax.inject.Inject

class GetHiddenUseCase @Inject constructor(
    private val repository: GamesRepository
) {

    suspend operator fun invoke(): List<Int> = repository.getHidden()

}