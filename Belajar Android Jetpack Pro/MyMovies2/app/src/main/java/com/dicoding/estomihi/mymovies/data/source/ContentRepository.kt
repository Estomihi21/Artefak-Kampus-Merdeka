package com.dicoding.estomihi.mymovies.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.estomihi.mymovies.data.source.remote.RemoteDataSource
import com.dicoding.estomihi.mymovies.data.source.remote.response.MovieResponse
import com.dicoding.estomihi.mymovies.data.source.remote.response.TvShowResponse
import com.dicoding.estomihi.mymovies.model.MovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ContentRepository private constructor(private val remoteDataSource: RemoteDataSource) : ContentDataSource {

    companion object {
        @Volatile
        private var instance: ContentRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): ContentRepository =
            instance ?: synchronized(this) {
                instance ?: ContentRepository(remoteDataSource).apply { instance = this }
            }
    }

    override fun getNowPlayingMovies(): LiveData<List<MovieEntity>> {
        val listMoviesResults = MutableLiveData<List<MovieEntity>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getMoviePlayingNow(object : RemoteDataSource.LoadMoviePlayingNowCallback {
                override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                    val moviesList = ArrayList<MovieEntity>()
                    for(response in movieResponse) {
                        val movie = MovieEntity(
                            response.id,
                            response.title,
                            response.overview,
                            response.posterPath,
                            response.backdropPath,
                            response.voteAverage,
                        )
                        moviesList.add(movie)
                    }
                    listMoviesResults.postValue(moviesList)
                }

            })
        }
        return listMoviesResults
    }

    override fun getDetailMovie(movieId: Int): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.LoadMovieDetailCallback {
                override fun onMovieDetailReceived(movieResponse: MovieResponse) {
                    val movie = MovieEntity(
                        movieResponse.id,
                        movieResponse.title,
                        movieResponse.overview,
                        movieResponse.posterPath,
                        movieResponse.backdropPath,
                        movieResponse.voteAverage
                    )
                    movieResult.postValue(movie)
                }

            })
        }
        return movieResult
    }

    override fun getOnTheAirTvShow(): LiveData<List<MovieEntity>> {
        val listTvShowResults = MutableLiveData<List<MovieEntity>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvShowOnTheAir(object : RemoteDataSource.LoadTvShowOnTheAirCallback {
                override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>) {
                    val tvShowList = ArrayList<MovieEntity>()
                    for (response in tvShowResponse) {
                        val tvShow = MovieEntity(
                            response.id,
                            response.title,
                            response.overview,
                            response.posterPath,
                            response.backdropPath,
                            response.voteAverage
                        )
                        tvShowList.add(tvShow)
                    }
                    listTvShowResults.postValue(tvShowList)
                }

            })
        }
        return listTvShowResults
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<MovieEntity> {
        val tvShowResult = MutableLiveData<MovieEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvShowDetail(tvShowId, object : RemoteDataSource.LoadTvShowDetailCallback {
                override fun onTvShowDetailReceived(tvShowResponse: TvShowResponse) {
                    val tvShow = MovieEntity(
                        tvShowResponse.id,
                        tvShowResponse.title,
                        tvShowResponse.overview,
                        tvShowResponse.posterPath,
                        tvShowResponse.backdropPath,
                        tvShowResponse.voteAverage,
                    )
                    tvShowResult.postValue(tvShow)
                }

            })
        }
        return tvShowResult
    }
}