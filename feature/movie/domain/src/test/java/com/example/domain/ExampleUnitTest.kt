package com.example.domain

import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val movieRepository: MovieRepository = mock()
    private lateinit var getMovieUseCase: GetMovieUseCase
//    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        getMovieUseCase = GetMovieUseCase(movieRepository)
//        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `repository returns data`() = runTest {
        val query = "harry"
        val movieList = listOf<Movie>(Movie(1, ""), Movie(2, ""))
        `when`(movieRepository.getMovieList(query)).thenReturn(movieList)

        // Act
        val flow = getMovieUseCase(query)

        flow.collectLatest {

        }

//        // Assert
//        flow.test {
//            assertEquals(UiEvent.Loading<List<Movie>>(), awaitItem()) // First item is Loading
//            assertEquals(UiEvent.Success(movies), awaitItem()) // Second item is Success
//            awaitComplete() // Ensure the flow completes
//        }
    }
}