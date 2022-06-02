package com.slc.wordlegames.ui.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slc.wordlegames.domain.model.Game
import com.slc.wordlegames.domain.usecase.GetGamesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
): ViewModel() {

    val games: MutableLiveData<Result<List<Game>>> = MutableLiveData()

    fun getGames() {
        viewModelScope.launch {
            val list = getGamesUseCase()
            games.postValue(Result.success(list))
        }
    }

}