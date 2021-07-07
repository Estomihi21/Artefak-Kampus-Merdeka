package com.dicoding.estomihi.myfinaljetpack.home.movie

import com.dicoding.estomihi.myfinaljetpack.source.local.entity.MovieEntity

interface MovieCallback {
    fun onItemClicked(data : MovieEntity)
}