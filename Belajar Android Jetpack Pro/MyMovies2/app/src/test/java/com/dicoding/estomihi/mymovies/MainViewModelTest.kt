package com.dicoding.estomihi.mymovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.estomihi.mymovies.data.source.ContentRepository
import com.dicoding.estomihi.mymovies.model.MovieEntity
import com.dicoding.estomihi.mymovies.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShows()

    private lateinit var viewModel : MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MainViewModel(contentRepository)
    }

    @Test
    fun getListNowPlayingMovies() {
        val movie = MutableLiveData<List<MovieEntity>>()
        movie.value = dummyMovie

        Mockito.`when`(contentRepository.getNowPlayingMovies()).thenReturn(movie)

        val movieEntities = viewModel.getListNowPlayingMovies().value

        verify(contentRepository).getNowPlayingMovies()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(10, movieEntities?.size)

        viewModel.getListNowPlayingMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getListOnTheAirTvShows() {
        val tvShow = MutableLiveData<List<MovieEntity>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(contentRepository.getOnTheAirTvShow()).thenReturn(tvShow)

        val tvShowEntities = viewModel.getListOnTheAirTvShows().value

        verify(contentRepository).getOnTheAirTvShow()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(10, tvShowEntities?.size)

        viewModel.getListOnTheAirTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}