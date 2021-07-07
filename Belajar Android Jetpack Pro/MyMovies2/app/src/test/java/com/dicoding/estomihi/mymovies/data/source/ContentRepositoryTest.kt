package com.dicoding.estomihi.mymovies.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.estomihi.mymovies.data.source.remote.RemoteDataSource
import com.dicoding.estomihi.mymovies.utils.DataDummy
import com.dicoding.estomihi.mymovies.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class ContentRepositoryTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val contentRepository = FakeContentRepository(remote)

    private val dummyMovieResponse = DataDummy.generateDataDummyMovieResponse()
    private val dummyTvShowResponse = DataDummy.generateDataDummyTvShowResponse()
    private val movieId = dummyMovieResponse[0].id
    private val tvShowId = dummyTvShowResponse[0].id

    private val movieResponse = DataDummy.generateDataDummyMovieResponse()[0]
    private val tvShowResponse = DataDummy.generateDataDummyTvShowResponse()[0]


    @Test
    fun getNowPlayingMovies() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadMoviePlayingNowCallback)
                    .onAllMoviesReceived(dummyMovieResponse)
                null
            }.`when`(remote).getMoviePlayingNow(any())
        }

        val movieEntities = LiveDataTestUtil.getValue(contentRepository.getNowPlayingMovies())

        runBlocking {
            verify(remote).getMoviePlayingNow(any())
        }

        Assert.assertNotNull(movieEntities)
        assertEquals(dummyMovieResponse.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback)
                    .onMovieDetailReceived(movieResponse)
                null
            }.`when`(remote).getMovieDetail(eq(movieId), any())
        }

        val movieEntities = LiveDataTestUtil.getValue(contentRepository.getDetailMovie(movieId))

        runBlocking {
            verify(remote).getMovieDetail(eq(movieId), any())
        }

        Assert.assertNotNull(movieEntities)
        assertEquals(movieResponse.id, movieEntities.id)
    }

    @Test
    fun getOnTheAirTvShow() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadTvShowOnTheAirCallback)
                    .onAllTvShowsReceived(dummyTvShowResponse)
                null
            }.`when`(remote).getTvShowOnTheAir(any())
        }

        val tvShowEntities = LiveDataTestUtil.getValue(contentRepository.getOnTheAirTvShow())

        runBlocking {
            verify(remote).getTvShowOnTheAir(any())
        }

        Assert.assertNotNull(tvShowEntities)
        assertEquals(dummyTvShowResponse.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getDetailTvShow() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback)
                    .onTvShowDetailReceived(tvShowResponse)
                null
            }.`when`(remote).getTvShowDetail(eq(tvShowId), any())
        }

        val tvShowEntities = LiveDataTestUtil.getValue(contentRepository.getDetailTvShow(tvShowId))

        runBlocking {
            verify(remote).getTvShowDetail(eq(tvShowId), any())
        }

        Assert.assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.id, tvShowEntities.id)
    }
}