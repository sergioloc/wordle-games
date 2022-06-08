package com.slc.wordlegames.ui.hidden

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slc.wordlegames.domain.model.Game
import com.slc.wordlegames.domain.usecase.GetGamesUseCase
import com.slc.wordlegames.domain.usecase.GetHiddenUseCase
import com.slc.wordlegames.domain.usecase.SetHiddenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class HiddenViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase,
    private val getHiddenUseCase: GetHiddenUseCase,
    private val setHiddenUseCase: SetHiddenUseCase
): ViewModel() {

    val games: MutableLiveData<Result<List<Game>>> = MutableLiveData()
    val hide: MutableLiveData<Result<Boolean>> = MutableLiveData()

    fun getHiddenGames() {
        viewModelScope.launch {
            val list = getGamesUseCase()
            val hidden = getHiddenUseCase()
            val result = ArrayList<Game>()

            for (item in list)
                if (hidden.contains(item.id))
                    result.add(item)

            games.postValue(Result.success(result))
        }
    }

    fun setGameHidden(id: Int, visible: Boolean) {
        viewModelScope.launch {
            setHiddenUseCase(id, visible)
            hide.postValue(Result.success(true))
        }
    }

}