package com.example.data.repository

import com.example.data.mapper.toMovie
import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import com.example.network.services.MovieService

class MovieRepositoryImpl(private val movieService: MovieService) : MovieRepository {
    override suspend fun getMovieList(query: String): List<Movie> {
        return movieService.getMovieList(query).toMovie()
    }
}