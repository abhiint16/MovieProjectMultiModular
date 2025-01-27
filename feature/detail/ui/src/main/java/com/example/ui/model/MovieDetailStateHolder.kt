package com.example.ui.model

import com.example.domain.model.MovieDetails

data class MovieDetailStateHolder(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: MovieDetails? = null
)
