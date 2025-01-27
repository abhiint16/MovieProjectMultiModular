package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.UiStatus
import com.example.domain.usecase.GetMovieUseCase
import com.example.ui.model.MovieStateHolder
import com.example.ui.model.MovieUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val getMovieUseCase: GetMovieUseCase) :
    ViewModel() {
    private val _movieStateFlow = MutableStateFlow(MovieStateHolder())
    val movieStateFlow: StateFlow<MovieStateHolder> = _movieStateFlow

    private val _event = MutableSharedFlow<MovieUiEvent>()
    val event: SharedFlow<MovieUiEvent> = _event

    private val _movieQuery = MutableStateFlow("harr")
    val movieQuery: StateFlow<String> = _movieQuery

    init {
        viewModelScope.launch {
            _movieQuery.debounce(500).distinctUntilChanged().collectLatest {
                getMovieList(movieQuery.value)
            }
        }
    }

    fun setQuery(query: String) {
        _movieQuery.value = query
    }

    private fun getMovieList(query: String) {
        viewModelScope.launch {
            getMovieUseCase.invoke(query).collect {
                when (it) {
                    is UiStatus.Loading -> {
                        _movieStateFlow.value = MovieStateHolder(isLoading = true)
                        _event.emit(MovieUiEvent.ShowToast("Loading ..."))
                    }

                    is UiStatus.Success -> {
                        _movieStateFlow.value = MovieStateHolder(data = it.data)
                        _event.emit(MovieUiEvent.ShowSnackbar("Success"))
                    }

                    is UiStatus.Failure -> {
                        _movieStateFlow.value = MovieStateHolder(errorMessage = it.message)
                        _event.emit(MovieUiEvent.ShowToast("Error ... ${it.message}"))
                    }
                }
            }
        }
    }
}