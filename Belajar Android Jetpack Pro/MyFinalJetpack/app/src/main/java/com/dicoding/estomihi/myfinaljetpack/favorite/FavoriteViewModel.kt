package com.dicoding.estomihi.myfinaljetpack.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.estomihi.myfinaljetpack.source.ContentRepository
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.MovieEntity
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.TvShowEntity
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val contentRepository: ContentRepository) : ViewModel() {

    fun getListFavoriteMovie(): LiveData<PagedList<MovieEntity>> = contentRepository.getListFavoriteMovie()

    fun getListFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> = contentRepository.getListFavoriteTvShow()

    fun setFavoriteMovie(movie: MovieEntity) {
        contentRepository.setFavoriteMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity) {
        contentRepository.setFavoriteTvShow(tvShow)
    }

}