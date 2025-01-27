package com.example.ui.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.UiStatus
import com.example.domain.usecase.DownloadImageUseCase
import com.example.domain.usecase.GetMovieDetailUseCase
import com.example.ui.model.DetailUiEvent
import com.example.ui.model.MovieDetailStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retry
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val downloadImageUseCase: DownloadImageUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _movieDetailState = MutableStateFlow(MovieDetailStateHolder())
    val movieDetailState: StateFlow<MovieDetailStateHolder> = _movieDetailState

    private val _event = MutableSharedFlow<DetailUiEvent>()
    val event: SharedFlow<DetailUiEvent> = _event

    init {
        val id = savedStateHandle.get<String>("id")
        id?.let {
            getMovieDetail(it)
        }
//        savedStateHandle.getLiveData<String>("id").observeForever {
//            it?.let {
//                getMovieDetail(it)
//            }
//        }
    }

    private fun getMovieDetail(id: String) {
        //if flow comes to catch, it retries for one more time
        getMovieDetailUseCase.invoke(id).onEach {
            when (it) {
                is UiStatus.Loading -> {
                    _movieDetailState.value = MovieDetailStateHolder(isLoading = true)
                }

                is UiStatus.Success -> {
                    _movieDetailState.value = MovieDetailStateHolder(data = it.data)
                }

                is UiStatus.Failure -> {
                    _movieDetailState.value = MovieDetailStateHolder(error = it.message)
                }
            }
        }.retry(1).launchIn(viewModelScope)
    }

    fun downloadClick(imageUrl: String) {
        downloadImageUseCase.invoke(downloadUrl = imageUrl ).onEach {
            when (it) {
                is UiStatus.Loading -> {
                    _event.emit(DetailUiEvent.ShowToast("Downloading ..."))
                }

                is UiStatus.Success -> {
                    _event.emit(DetailUiEvent.ShowToast("Download Completed ..."))
                }

                is UiStatus.Failure -> {
                    _event.emit(DetailUiEvent.ShowToast("Download Failed ..."))
                }
            }
        }.launchIn(viewModelScope)
    }
}