package com.dicoding.estomihi.mymovies.detail

import com.dicoding.estomihi.mymovies.utils.DataDummy
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getDetailMovie() {
        val movie = viewModel.getDetailMovie()
        Assert.assertNotNull(movie)
        assertEquals(dummyMovie.id, movie.id)
        assertEquals(dummyMovie.imgPoster, movie.imgPoster)
        assertEquals(dummyMovie.title, movie.title)
        assertEquals(dummyMovie.nameDirector, movie.nameDirector)
        assertEquals(dummyMovie.rating, movie.rating, 0.0)
        assertEquals(dummyMovie.genre, movie.genre)
        assertEquals(dummyMovie.descStory, movie.descStory)
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = viewModel.getDetailTvShow()
        Assert.assertNotNull(tvShow)
        assertEquals(dummyTvShow.id, tvShow.id)
        assertEquals(dummyTvShow.imgPoster, tvShow.imgPoster)
        assertEquals(dummyTvShow.title, tvShow.title)
        assertEquals(dummyTvShow.nameDirector, tvShow.nameDirector)
        assertEquals(dummyTvShow.rating, tvShow.rating, 0.0)
        assertEquals(dummyTvShow.genre, tvShow.genre)
        assertEquals(dummyTvShow.descStory, tvShow.descStory)
    }
}