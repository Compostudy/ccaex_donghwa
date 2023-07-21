package com.compostudy.android.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compostudy.android.common.model.response.DiscoverMovieResult
import com.compostudy.android.domain.usecase.DiscoverMovieResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val discoverMovieResultUseCase: DiscoverMovieResultUseCase,
) : ViewModel() {

    private val _movieResults: MutableStateFlow<List<DiscoverMovieResult>> =
        MutableStateFlow(emptyList())
    val movieResults: StateFlow<List<DiscoverMovieResult>> get() = _movieResults

    fun getMovieResults() {
        viewModelScope.launch {
            val movieResult = discoverMovieResultUseCase.invoke()
            _movieResults.value = movieResult
        }
    }

}