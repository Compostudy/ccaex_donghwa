package com.compostudy.android.data.repository

import com.compostudy.android.common.model.response.DiscoverMovieResult
import com.compostudy.android.data.api.MovieApi
import com.compostudy.android.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
) : MovieRepository {

    override suspend fun getResults(): List<DiscoverMovieResult> {
        val response = movieApi.discoverMovie()
        return response.results
    }
}