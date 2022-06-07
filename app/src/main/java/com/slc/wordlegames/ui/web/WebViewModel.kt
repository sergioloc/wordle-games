package com.slc.wordlegames.ui.web

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slc.wordlegames.domain.model.History
import com.slc.wordlegames.domain.usecase.SaveHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebViewModel @Inject constructor(
    private val saveHistoryUseCase: SaveHistoryUseCase
): ViewModel() {

    val saved: MutableLiveData<Result<Boolean>> = MutableLiveData()

    fun saveHistory(history: History) {
        viewModelScope.launch {
            saveHistoryUseCase(history)
            saved.postValue(Result.success(true))
        }
    }

}