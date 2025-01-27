package com.example.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

@Composable
fun MovieImageFullScreen(viewModel: DetailViewModel) {
    val state = viewModel.movieDetailState.collectAsStateWithLifecycle()

    Scaffold(topBar = { Text(text = "Movie Detail") }) {
        println(it)
        if (state.value.isLoading) {
            LoadingState()
        } else if (state.value.error.isNullOrBlank().not()) {
            ErrorState(state.value.error)
        }
        state.value.data?.let { data ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = data.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}