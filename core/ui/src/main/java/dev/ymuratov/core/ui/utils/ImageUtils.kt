package dev.ymuratov.core.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@Composable
fun createImageLoader(): ImageLoader {
    val context = LocalContext.current
    val okHttpClient = OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS).retryOnConnectionFailure(true).build()
    val imageLoader = ImageLoader.Builder(context).okHttpClient { okHttpClient }.crossfade(true).build()
    return imageLoader
}