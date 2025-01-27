package com.example.domain.usecase

import com.example.common.UiStatus
import com.example.domain.model.MovieDetails
import com.example.domain.repository.MovieDetailRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val movieDetailRepo: MovieDetailRepo) {

    operator fun invoke(movieId: String) = flow<UiStatus<MovieDetails>> {
        emit(UiStatus.Loading())
        emit(UiStatus.Success(movieDetailRepo.getMovieDetail(movieId)))
    }.catch {
        emit(UiStatus.Failure(it.message))
    }.flowOn(Dispatchers.IO)
}