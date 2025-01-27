package com.example.domain.repository

import com.example.domain.model.MovieDetails

interface MovieDetailRepo {

    suspend fun getMovieDetail(movieId: String): MovieDetails
}