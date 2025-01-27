package com.example.data.mapper

import com.example.domain.model.MovieDetails
import com.example.network.model.MovieDetailDTO

fun MovieDetailDTO.toMovieDetail(): MovieDetails {
    return MovieDetails(
        desc = this.overview,
        title = this.title,
        imageUrl = getFullImagePath(this.poster_path),
        id = this.id
    )
}

fun getFullImagePath(posterPath: String) = "https://image.tmdb.org/t/p/w500/${posterPath}"