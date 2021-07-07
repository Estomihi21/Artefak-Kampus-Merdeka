package com.dicoding.estomihi.myfinaljetpack.home.tvshow

import com.dicoding.estomihi.myfinaljetpack.source.local.entity.TvShowEntity

interface TvShowCallback {
    fun onItemClicked(data : TvShowEntity)
}