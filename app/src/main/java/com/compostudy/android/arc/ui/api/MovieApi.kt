package com.compostudy.android.arc.ui.api

import com.compostudy.android.arc.ui.model.DiscoverMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    companion object {
        const val API_KEY = "78f09933ae6d289319bd8eeccafacf23"
    }

    @GET("discover/movie")
    suspend fun discoverMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): DiscoverMovieResponse
}