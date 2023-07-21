package com.compostudy.android.domain.usecase

import com.compostudy.android.common.model.response.DiscoverMovieResult
import com.compostudy.android.domain.repository.MovieRepository
import javax.inject.Inject

class DiscoverMovieResultUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {

    suspend operator fun invoke(): List<DiscoverMovieResult> {
        return movieRepository.getResults()
    }
}