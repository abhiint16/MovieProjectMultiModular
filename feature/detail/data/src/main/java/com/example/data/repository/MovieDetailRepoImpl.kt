package com.example.data.repository

import com.example.data.mapper.toMovieDetail
import com.example.domain.model.MovieDetails
import com.example.domain.repository.MovieDetailRepo
import com.example.network.services.MovieService
import javax.inject.Inject

class MovieDetailRepoImpl @Inject constructor(private val movieService: MovieService) :
    MovieDetailRepo {
    override suspend fun getMovieDetail(movieId: String): MovieDetails {
        return movieService.getMovieDetail(movieId).toMovieDetail()
    }
}