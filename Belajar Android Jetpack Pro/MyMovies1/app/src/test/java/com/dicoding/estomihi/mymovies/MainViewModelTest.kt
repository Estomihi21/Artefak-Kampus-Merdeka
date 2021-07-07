package com.dicoding.estomihi.mymovies

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }

    @Test
    fun getListMovie() {
        val movies = viewModel.getListMovie()
        Assert.assertNotNull(movies)
        Assert.assertEquals(10, movies.size)
    }

    @Test
    fun getListTvShow() {
        val tvShows = viewModel.getListTvShow()
        Assert.assertNotNull(tvShows)
        Assert.assertEquals(10, tvShows.size)
    }
}