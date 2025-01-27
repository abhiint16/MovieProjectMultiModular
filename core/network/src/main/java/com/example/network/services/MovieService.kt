package com.example.network.services

import com.example.network.model.MovieDetailDTO
import com.example.network.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("3/search/movie")
    suspend fun getMovieList(
        @Query("query") query: String,
//        @Query("page") page: Int,
        @Query("api_key") apiKey: String? = "5ed67308495947fbcc0f1ab9837c243d"
    ): MovieResponse

    @GET("3/movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String? = "5ed67308495947fbcc0f1ab9837c243d",
    ): MovieDetailDTO
}