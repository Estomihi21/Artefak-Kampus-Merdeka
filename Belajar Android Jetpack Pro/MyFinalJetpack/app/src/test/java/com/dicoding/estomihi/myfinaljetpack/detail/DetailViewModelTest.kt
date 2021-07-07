package com.dicoding.estomihi.myfinaljetpack.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.estomihi.myfinaljetpack.source.ContentRepository
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.MovieEntity
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.TvShowEntity
import com.dicoding.estomihi.myfinaljetpack.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
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
    private val movieId = dummyMovie.movieId
    private val tvShowId = dummyTvShow.tvShowId

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Mock
    private lateinit var observerMovie: Observer<MovieEntity>

    @Mock
    private lateinit var observerTvShow: Observer<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(contentRepository)
    }

    @Test
    fun getMovieDetail() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        Mockito.`when`(contentRepository.getDetailMovie(movieId)).thenReturn(movie)

        val movieEntities = viewModel.getMovieDetail(movieId).value

        Assert.assertNotNull(movieEntities)
        assertEquals(dummyMovie.id, movieEntities?.id)
        assertEquals(dummyMovie.movieId, movieEntities?.movieId)
        assertEquals(dummyMovie.title, movieEntities?.title)
        assertEquals(dummyMovie.posterPath, movieEntities?.posterPath)
        assertEquals(dummyMovie.backdropPath, movieEntities?.backdropPath)
        assertEquals(dummyMovie.overview, movieEntities?.overview)
        assertEquals(dummyMovie.voteAverage, movieEntities?.voteAverage)

        viewModel.getMovieDetail(movieId).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getTvShowDetail() {
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShow

        Mockito.`when`(contentRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow)

        val tvShowEntities = viewModel.getTvShowDetail(tvShowId).value

        Assert.assertNotNull(tvShowEntities)
        assertEquals(dummyTvShow.id, tvShowEntities?.id)
        assertEquals(dummyTvShow.tvShowId, tvShowEntities?.id)
        assertEquals(dummyTvShow.title, tvShowEntities?.title)
        assertEquals(dummyTvShow.posterPath, tvShowEntities?.posterPath)
        assertEquals(dummyTvShow.backdropPath, tvShowEntities?.backdropPath)
        assertEquals(dummyTvShow.overview, tvShowEntities?.overview)
        assertEquals(dummyTvShow.voteAverage, tvShowEntities?.voteAverage)

        viewModel.getTvShowDetail(tvShowId).observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyTvShow)
    }
}