package com.example.data.mapper

import com.example.common.Mapper
import com.example.domain.model.Movie
import com.example.network.model.MovieDTO
import com.example.network.model.MovieResponse

//class MovieMapper : Mapper<MovieDTO, Movie> {
//    override fun map(from: MovieDTO): Movie {
//        return Movie(id = from.id, imageUrl = makeFullUrl(from.poster_path))
//    }
//}

fun MovieResponse.toMovie(): List<Movie> {
    return this.results.map {
        Movie(id = it.id, imageUrl = makeFullUrl(it.poster_path))
    }
}

fun makeFullUrl(path: String?) = "https://image.tmdb.org/t/p/w500/${path}"