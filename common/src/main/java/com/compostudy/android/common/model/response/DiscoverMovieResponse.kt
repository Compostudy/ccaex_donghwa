package com.compostudy.android.common.model.response

data class DiscoverMovieResponse(
    val page: Int,
    val results: List<DiscoverMovieResult>
)