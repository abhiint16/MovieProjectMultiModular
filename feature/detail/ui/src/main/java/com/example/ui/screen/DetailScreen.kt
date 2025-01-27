package com.example.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.domain.model.MovieDetails

@Composable
fun MovieDetailScreen(id: String, viewModel: DetailViewModel, navController: NavController) {
    val state = viewModel.movieDetailState.collectAsStateWithLifecycle()

    DisposableEffect(Unit) {
        println("ASDFG -> DisposableEffect")
        onDispose {
            println("ASDFG -> DisposableEffect -> onDispose")
        }
    }

    LaunchedEffect(Unit) {
        println("ASDFG -> LaunchedEffect")
    }

    SideEffect {
        println("ASDFG -> SideEffect")
    }

    Scaffold(topBar = { Text(text = "Movie Detail") }) {
        println(it)
        if (state.value.isLoading) {
            LoadingState()
        } else if (state.value.error.isNullOrBlank().not()) {
            ErrorState(state.value.error)
        }
        state.value.data?.let {
            ContentState(it, itemClick = { id ->
                navController.navigate("movie_details_fullscreen_route/{id}")
            }) { id ->
                viewModel.downloadClick(id)
            }
        }
    }
}

@Composable
fun ContentState(
    data: MovieDetails,
    itemClick: (id: Int) -> Unit,
    downloadClick: (imageUrl: String) -> Unit
) {
    Column {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clickable {
                    itemClick.invoke(data.id)
                },
            model = data.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = data.title,
            fontStyle = FontStyle.Normal,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = data.desc,
            fontStyle = FontStyle.Normal,
            style = MaterialTheme.typography.bodyMedium
        )
        Button(
            onClick = { downloadClick.invoke(data.imageUrl) }
        ) {
            Text(text = "Download Image")
        }

    }
}

@Composable
fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorState(errorMessage: String?) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = errorMessage ?: "")
    }
}
