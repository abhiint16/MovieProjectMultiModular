package com.example.ui.model

sealed class MovieUiEvent {
    class ShowSnackbar(val message: String) : MovieUiEvent()
    class ShowToast(val message: String) : MovieUiEvent()
}