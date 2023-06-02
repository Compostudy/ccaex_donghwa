package com.compostudy.android.arc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.compostudy.android.arc.ui.api.MovieApi
import com.compostudy.android.arc.ui.model.DiscoverMovieResponse
import com.compostudy.android.arc.ui.model.DiscoverMovieResult
import com.compostudy.android.arc.ui.theme.CcaexTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class MainActivity : ComponentActivity() {

    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CcaexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val BASE_URL = "https://api.themoviedb.org/3/"

                    val httpClient = OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            val request = chain.request()
                            val newRequest = request.newBuilder()
                                .header("User-Agent", "MyCustomUserAgent")
                                .build()
                            chain.proceed(newRequest)
                        }
                        .build()

                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient)
                        .build()

                    val api = retrofit.create(MovieApi::class.java)

                    LaunchedEffect(key1 = Unit) {
                        val response = api.discoverMovie(MovieApi.API_KEY)
                        val results = response.results
                        Log.d("results", response.toString())
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CcaexTheme {

    }
}