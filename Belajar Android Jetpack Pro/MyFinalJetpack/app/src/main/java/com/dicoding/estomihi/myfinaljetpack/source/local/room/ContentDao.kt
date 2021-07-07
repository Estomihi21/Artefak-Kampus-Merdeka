package com.dicoding.estomihi.myfinaljetpack.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.MovieEntity
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.TvShowEntity

@Dao
interface ContentDao {

    @Query("SELECT * FROM favorite_movie")
    fun getListMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM favorite_tvShow")
    fun getListTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM favorite_movie WHERE movieIsFavorite = 1")
    fun getListFavoriteMovie() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM favorite_tvShow WHERE tvShowIsFavorite = 1")
    fun getListFavoriteTvShow() : DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM favorite_movie WHERE movieId = :movieId")
    fun getDetailMovieById(movieId: Int) : LiveData<MovieEntity>

    @Query("SELECT * FROM favorite_tvShow WHERE tvShowId = :tvShowId")
    fun getDetailTvShowById(tvShowId: Int) : LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovie(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowEntity::class)
    fun insertTvShow(tvShows: List<TvShowEntity>)

    @Update(entity = MovieEntity::class)
    fun updateMovie(movie : MovieEntity)

    @Update(entity = TvShowEntity::class)
    fun updateTvShow(tvShow: TvShowEntity)

}