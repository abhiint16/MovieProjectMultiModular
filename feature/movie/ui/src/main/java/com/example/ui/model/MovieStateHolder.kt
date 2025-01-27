package com.example.ui.model

import com.example.domain.model.Movie

data class MovieStateHolder(
    val isLoading: Boolean = false,
    val data: List<Movie>? = null,
    val errorMessage: String? = null
)
