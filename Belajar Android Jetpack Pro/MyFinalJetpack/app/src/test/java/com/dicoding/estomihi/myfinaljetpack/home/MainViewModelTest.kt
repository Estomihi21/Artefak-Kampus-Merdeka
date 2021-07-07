package com.dicoding.estomihi.myfinaljetpack.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.estomihi.myfinaljetpack.source.ContentRepository
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.MovieEntity
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.TvShowEntity
import com.dicoding.estomihi.myfinaljetpack.vo.Resource
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

    private lateinit var viewModel : MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Mock
    private lateinit var observerMovie: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var observerTvShow: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = MainViewModel(contentRepository)
    }

    @Test
    fun getListNowPlayingMovies() {
        val dummyMovie = Resource.success(moviePagedList)
        Mockito.`when`(dummyMovie.data?.size).thenReturn(5)
        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = dummyMovie

        Mockito.`when`(contentRepository.getNowPlayingMovies()).thenReturn(movie)

        val movieEntities = viewModel.getListNowPlayingMovies().value?.data

        verify(contentRepository).getNowPlayingMovies()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(5, movieEntities?.size)

        viewModel.getListNowPlayingMovies().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getListOnTheAirTvShows() {
        val dummyTvShow = Resource.success(tvShowPagedList)
        Mockito.`when`(dummyTvShow.data?.size).thenReturn(5)
        val tvShow = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(contentRepository.getOnTheAirTvShow()).thenReturn(tvShow)

        val tvShowEntities = viewModel.getListOnTheAirTvShows().value?.data

        verify(contentRepository).getOnTheAirTvShow()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(5, tvShowEntities?.size)

        viewModel.getListOnTheAirTvShows().observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyTvShow)
    }
}