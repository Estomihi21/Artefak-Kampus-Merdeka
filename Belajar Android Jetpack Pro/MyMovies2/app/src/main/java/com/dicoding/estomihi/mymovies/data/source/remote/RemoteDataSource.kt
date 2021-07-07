package com.dicoding.estomihi.mymovies.data.source.remote

import com.dicoding.estomihi.mymovies.data.source.remote.api.ApiConfig
import com.dicoding.estomihi.mymovies.data.source.remote.response.MovieResponse
import com.dicoding.estomihi.mymovies.data.source.remote.response.TvShowResponse
import com.dicoding.estomihi.mymovies.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {
    companion object {
        private val instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    suspend fun getMoviePlayingNow(
       callback: LoadMoviePlayingNowCallback
    ) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getMoviesNowPlaying()
        client.await().results?.let {
            callback.onAllMoviesReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailMovie(movieId)
        client.await().let {
            callback.onMovieDetailReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowOnTheAir(
        callback: LoadTvShowOnTheAirCallback
    ) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getTvShowsOnTheAir()
        client.await().results?.let {
            callback.onAllTvShowsReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowDetail(tvShowId: Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailTvShow(tvShowId)
        client.await().let {
            callback.onTvShowDetailReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadMoviePlayingNowCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieResponse: MovieResponse)
    }

    interface LoadTvShowOnTheAirCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvShowResponse: TvShowResponse)
    }
}