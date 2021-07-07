package com.dicoding.estomihi.myfinaljetpack.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.estomihi.myfinaljetpack.source.ContentRepository
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.MovieEntity
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.TvShowEntity
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Mock
    private lateinit var observerMovie: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var observerTvShow: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(contentRepository)
    }

    @Test
    fun getListFavoriteMovie() {
        val dummyFavMovie = moviePagedList
        Mockito.`when`(dummyFavMovie.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyFavMovie

        Mockito.`when`(contentRepository.getListFavoriteMovie()).thenReturn(movie)
        val movieEntities = viewModel.getListFavoriteMovie().value
        verify(contentRepository).getListFavoriteMovie()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(5, movieEntities?.size)

        viewModel.getListFavoriteMovie().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyFavMovie)
    }

    @Test
    fun getListFavoriteTvShow() {
        val dummyFavTvShow = tvShowPagedList
        Mockito.`when`(dummyFavTvShow.size).thenReturn(5)
        val tvShow = MutableLiveData<PagedList<TvShowEntity>>()
        tvShow.value = dummyFavTvShow

        Mockito.`when`(contentRepository.getListFavoriteTvShow()).thenReturn(tvShow)
        val tvShowEntities = viewModel.getListFavoriteTvShow().value
        verify(contentRepository).getListFavoriteTvShow()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(5, tvShowEntities?.size)

        viewModel.getListFavoriteTvShow().observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyFavTvShow)
    }
}