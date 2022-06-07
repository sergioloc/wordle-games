package com.slc.wordlegames.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slc.wordlegames.domain.model.History
import com.slc.wordlegames.domain.usecase.GetHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
): ViewModel() {

    val history: MutableLiveData<Result<List<History>>> = MutableLiveData()

    fun getHistory(type: Int) {
        viewModelScope.launch {
            val response = getHistoryUseCase(type)
            history.postValue(Result.success(response))
        }
    }

}