package com.dicoding.estomihi.myfinaljetpack.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.estomihi.myfinaljetpack.source.local.LocalDataSource
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.MovieEntity
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.TvShowEntity
import com.dicoding.estomihi.myfinaljetpack.source.remote.RemoteDataSource
import com.dicoding.estomihi.myfinaljetpack.utils.DataDummy
import com.dicoding.estomihi.myfinaljetpack.utils.LiveDataTestUtil
import com.dicoding.estomihi.myfinaljetpack.utils.PagedListUtil
import com.dicoding.estomihi.myfinaljetpack.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class ContentRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val contentRepository = FakeContentRepository(remote, local)

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShows()

    private val movie = DataDummy.generateDummyMovies()[0]
    private val tvShow = DataDummy.generateDummyTvShows()[0]


    @Test
    fun getNowPlayingMovies() {
        val dataSourceMovie = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getListMovie()).thenReturn(dataSourceMovie)
        contentRepository.getNowPlayingMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getListMovie()

        Assert.assertNotNull(movieEntities.data)
        assertEquals(dummyMovie.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movie
        Mockito.`when`(local.getDetailMovie(movie.movieId)).thenReturn(dummyMovie)

        val movieEntities = LiveDataTestUtil.getValue(contentRepository.getDetailMovie(movie.movieId))
        verify(local).getDetailMovie(movie.movieId)

        Assert.assertNotNull(movieEntities)
        assertEquals(movie.movieId, movieEntities.movieId)
    }

    @Test
    fun getListFavoriteMovie() {
        val dataSourceMovie = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getListFavoriteMovie()).thenReturn(dataSourceMovie)
        contentRepository.getListFavoriteMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getListFavoriteMovie()

        Assert.assertNotNull(movieEntities.data)
        assertEquals(dummyMovie.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getOnTheAirTvShow() {
        val dataSourceTvShow = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getListTvShow()).thenReturn(dataSourceTvShow)
        contentRepository.getOnTheAirTvShow()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getListTvShow()

        Assert.assertNotNull(tvShowEntities.data)
        assertEquals(dummyTvShow.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailTvShow() {
        val dummyTvShow = MutableLiveData<TvShowEntity>()
        dummyTvShow.value = tvShow
        Mockito.`when`(local.getDetailTvShow(tvShow.tvShowId)).thenReturn(dummyTvShow)

        val tvShowEntities = LiveDataTestUtil.getValue(contentRepository.getDetailTvShow(tvShow.tvShowId))
        verify(local).getDetailTvShow(tvShow.tvShowId)

        Assert.assertNotNull(tvShowEntities)
        assertEquals(tvShow.tvShowId, tvShowEntities.tvShowId)
    }

    @Test
    fun getListFavoriteTvShow() {
        val dataSourceTvShow = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        Mockito.`when`(local.getListFavoriteTvShow()).thenReturn(dataSourceTvShow)
        contentRepository.getListFavoriteTvShow()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getListFavoriteTvShow()

        Assert.assertNotNull(tvShowEntities.data)
        assertEquals(dummyMovie.size.toLong(), tvShowEntities.data?.size?.toLong())
    }
}