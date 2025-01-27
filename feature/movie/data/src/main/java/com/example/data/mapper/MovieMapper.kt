package com.example.data.mapper

import com.example.domain.model.Movie
import com.example.network.model.MovieResponse

fun MovieResponse.toMovie(): List<Movie> {
    return this.results.map {
        Movie(id = it.id, imageUrl = makeFullUrl(it.poster_path))
    }
}

fun makeFullUrl(path: String?) = "https://image.tmdb.org/t/p/w500/${path}"