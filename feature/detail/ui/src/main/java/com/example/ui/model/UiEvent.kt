package com.example.ui.model

sealed class DetailUiEvent {
    class ShowSnackbar(val message: String) : DetailUiEvent()
    class ShowToast(val message: String) : DetailUiEvent()
}