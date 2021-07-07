package com.dicoding.estomihi.myfinaljetpack.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.estomihi.myfinaljetpack.source.remote.api.ApiService
import com.dicoding.estomihi.myfinaljetpack.source.remote.response.MovieResponse
import com.dicoding.estomihi.myfinaljetpack.source.remote.response.TvShowResponse
import com.dicoding.estomihi.myfinaljetpack.source.remote.vo.ApiResponse
import com.dicoding.estomihi.myfinaljetpack.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val contentApiService: ApiService) {

    fun getMoviePlayingNow() : LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovieResponse = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        CoroutineScope(IO).launch {
            try {
                val response = contentApiService.getMoviesNowPlaying().await()
                resultMovieResponse.postValue(ApiResponse.success(response.results!!))
            } catch (e : IOException) {
                resultMovieResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultMovieResponse
    }

    fun getTvShowOnTheAir() : LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        val resultTvShowResponse = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        CoroutineScope(IO).launch {
            try {
                val response = contentApiService.getTvShowsOnTheAir().await()
                resultTvShowResponse.postValue(ApiResponse.success(response.results!!))
            } catch (e : IOException) {
                resultTvShowResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultTvShowResponse
    }

}