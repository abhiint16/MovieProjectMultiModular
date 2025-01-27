package com.example.domain.usecase

import com.example.common.UiStatus
import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import javax.inject.Inject


class GetMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(query: String) = flow<UiStatus<List<Movie>>> {
        emit(UiStatus.Loading())
        emit(UiStatus.Success(movieRepository.getMovieList(query)))
    }.retry(1).catch {
        emit(UiStatus.Failure(it.message))
    }.flowOn(Dispatchers.IO)
}