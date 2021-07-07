package com.dicoding.estomihi.mymovies.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.estomihi.mymovies.data.source.ContentRepository
import com.dicoding.estomihi.mymovies.model.MovieEntity
import com.dicoding.estomihi.mymovies.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(contentRepository)
    }

    @Test
    fun getMovieDetail() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        Mockito.`when`(contentRepository.getDetailMovie(movieId)).thenReturn(movie)

        val movieEntities = viewModel.getMovieDetail(movieId).value as MovieEntity

        Assert.assertNotNull(movieEntities)
        assertEquals(dummyMovie.id, movieEntities.id)
        assertEquals(dummyMovie.title, movieEntities.title)
        assertEquals(dummyMovie.posterPath, movieEntities.posterPath)
        assertEquals(dummyMovie.backdropPath, movieEntities.backdropPath)
        assertEquals(dummyMovie.overview, movieEntities.overview)
        assertEquals(dummyMovie.voteAverage, movieEntities.voteAverage)

        viewModel.getMovieDetail(movieId).observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getTvShowDetail() {
        val tvShow = MutableLiveData<MovieEntity>()
        tvShow.value = dummyTvShow

        Mockito.`when`(contentRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow)

        val tvShowEntities = viewModel.getTvShowDetail(tvShowId).value as MovieEntity

        Assert.assertNotNull(tvShowEntities)
        assertEquals(dummyTvShow.id, tvShowEntities.id)
        assertEquals(dummyTvShow.title, tvShowEntities.title)
        assertEquals(dummyTvShow.posterPath, tvShowEntities.posterPath)
        assertEquals(dummyTvShow.backdropPath, tvShowEntities.backdropPath)
        assertEquals(dummyTvShow.overview, tvShowEntities.overview)
        assertEquals(dummyTvShow.voteAverage, tvShowEntities.voteAverage)

        viewModel.getTvShowDetail(tvShowId).observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}