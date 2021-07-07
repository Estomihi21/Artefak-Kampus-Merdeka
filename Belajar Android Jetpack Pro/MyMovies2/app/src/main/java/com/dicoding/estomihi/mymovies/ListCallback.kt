package com.dicoding.estomihi.mymovies

import com.dicoding.estomihi.mymovies.model.MovieEntity

interface ListCallback {
    fun onItemClicked(data : MovieEntity)
}