package com.compostudy.android.domain.repository

import com.compostudy.android.common.model.response.DiscoverMovieResult

interface MovieRepository {

    suspend fun getResults() : List<DiscoverMovieResult>
}