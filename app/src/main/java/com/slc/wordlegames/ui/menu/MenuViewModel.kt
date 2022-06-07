package com.slc.wordlegames.ui.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slc.wordlegames.domain.model.Game
import com.slc.wordlegames.domain.usecase.GetGamesUseCase
import com.slc.wordlegames.domain.usecase.GetHiddenUseCase
import com.slc.wordlegames.domain.usecase.IsGamePlayedUseCase
import com.slc.wordlegames.utils.extensions.simpleFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase,
    private val getHiddenUseCase: GetHiddenUseCase,
    private val isGamePlayedUseCase: IsGamePlayedUseCase
): ViewModel() {

    val games: MutableLiveData<Result<List<Game>>> = MutableLiveData()

    fun getGames() {
        viewModelScope.launch {
            val list = getGamesUseCase()
            val hidden = getHiddenUseCase()

            list.map { !hidden.contains(it.id) }
            list.map { it.status = isGamePlayedUseCase(it.id, Date().simpleFormat()) }

            games.postValue(Result.success(list))
        }
    }

}