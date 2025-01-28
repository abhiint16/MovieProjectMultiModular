package com.example.aliaimageloader.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.example.aliaimageloader.data.ImageLoader

@Composable
fun AliaAsyncImage(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    placeholder: @Composable (() -> Unit)? = null,
    error: @Composable (() -> Unit)? = null
) {
    val context = LocalContext.current
    val imageLoader = remember { ImageLoader(context) }
    var bitmap = remember { mutableStateOf<ImageBitmap?>(null) }
    var isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(url) {
        isLoading.value = true
        val loadedBitmap = imageLoader.load(url)
        bitmap.value = loadedBitmap?.asImageBitmap()
        isLoading.value = false
    }

    Box(modifier = modifier) {
        when {
            isLoading.value && placeholder != null -> placeholder()
            bitmap.value != null -> Image(
                bitmap = bitmap.value!!,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = contentScale
            )

            !isLoading.value && error != null -> error()
        }
    }
}