package com.example.jetpack_compose_all_in_one.data.tmdbapi.repository

import com.example.jetpack_compose_all_in_one.data.tmdbapi.APIMovies
import com.example.jetpack_compose_all_in_one.data.tmdbapi.TmdbResponse
import com.example.jetpack_compose_all_in_one.di.TMDBAPI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    @TMDBAPI private val apiMovies: APIMovies
): IMovieRepository {
    override fun getPopularMovies(page: Int) = flow {
        apiMovies.getPopularMovies(page).apply{
            body()?.let {
                emit(it)
            } ?: run{
                // Error handling later.
            }
        }
    }
}