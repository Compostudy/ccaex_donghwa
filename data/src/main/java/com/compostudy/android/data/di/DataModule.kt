package com.compostudy.android.data.di

import com.compostudy.android.data.api.MovieApi
import com.compostudy.android.data.repository.MovieRepositoryImpl
import com.compostudy.android.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        retrofit: Retrofit,
    ): MovieRepository {
        val movieApi = retrofit.create(MovieApi::class.java)
        return MovieRepositoryImpl(movieApi)
    }
}