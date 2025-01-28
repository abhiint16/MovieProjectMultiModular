package com.example.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aliaimageloader.ui.screens.AliaAsyncImage
import com.example.domain.model.Movie
import com.example.ui.model.MovieUiEvent
import com.example.ui.navigation.LocalLifecycleOwner
import com.example.ui.viewmodel.MovieViewModel
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun MovieScreen(viewModel: MovieViewModel, navController: NavController) {
    val state = viewModel.movieStateFlow.collectAsState()
    val queryState = viewModel.movieQuery.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current

//    DisposableEffect(lifecycleOwner) {
//        val observer = LifecycleEventObserver { _, event ->
//            when (event) {
//                Lifecycle.Event.ON_CREATE -> TODO()
//                Lifecycle.Event.ON_START -> TODO()
//                Lifecycle.Event.ON_RESUME -> TODO()
//                Lifecycle.Event.ON_PAUSE -> TODO()
//                Lifecycle.Event.ON_STOP -> TODO()
//                Lifecycle.Event.ON_DESTROY -> TODO()
//                Lifecycle.Event.ON_ANY -> TODO()
//            }
//        }
//
//        lifecycleOwner.lifecycle.addObserver(observer)
//
//        onDispose {
//            lifecycleOwner.lifecycle.removeObserver(observer)
//        }
//    }

    Scaffold(topBar = {
        TextField(value = queryState.value, onValueChange = { viewModel.setQuery(it) })
    }) {
        println(it)
        if (state.value.isLoading) {
            LoadingState()
        }
        state.value.errorMessage?.let {
            ErrorState(it)
        }

        state.value.data?.let {
            if (it.isEmpty()) {
                ErrorState("Nothing Found")
            } else {
                ContentState(it) { id ->
                    navController.navigate("movie_details/${id}")
                }
            }
        }
    }

    HandleSideEffect(viewModel.event)
}

@Composable
fun ContentState(movieList: List<Movie>, itemClick: (id: Int) -> Unit) {
    val state = rememberLazyGridState()

    LazyVerticalGrid(columns = GridCells.Fixed(3), state = state) {
        items(items = movieList, key = { it.id }) { movie ->
            AliaAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(1.dp)
                    .clickable {
                        itemClick.invoke(movie.id)
                    },
                url = movie.imageUrl,
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun ErrorState(it: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = it)
    }
}

@Composable
fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Loading ...")
    }
}

@Composable
fun ShowSnackBar() {
//    val scaffoldState = rememberScaffoldState()
}

@Composable
fun HandleSideEffect(event: SharedFlow<MovieUiEvent>) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        event.collect { event ->
            when (event) {
                is MovieUiEvent.ShowSnackbar -> {
//                   ShowSnackBar()
                }

                is MovieUiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
