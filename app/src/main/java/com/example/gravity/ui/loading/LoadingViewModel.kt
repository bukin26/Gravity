package com.example.gravity.ui.loading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gravity.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.gravity.data.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class LoadingViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _linksStateFlow: MutableStateFlow<LinksState> =
        MutableStateFlow(LinksState.Initial)
    val linksStateFlow: StateFlow<LinksState> = _linksStateFlow

    init {
        getLink()
    }

    private fun getLink() {
        viewModelScope.launch(Dispatchers.IO) {
            _linksStateFlow.emit(LinksState.Loading)
            delay(2000)
            when(val result = repository.getLinksFromApi()) {
                is Result.Error ->  _linksStateFlow.emit(LinksState.Error(result.message))
                is Result.Success -> _linksStateFlow.emit(LinksState.Success(result.data))
            }
        }
    }
}