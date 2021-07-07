package com.dicoding.estomihi.myfinaljetpack.di.favorite

import com.dicoding.estomihi.myfinaljetpack.favorite.movie.FavoriteMovieFragment
import com.dicoding.estomihi.myfinaljetpack.favorite.tvshow.FavoriteTvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeFavoriteMovieFragment() : FavoriteMovieFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteTvShowFragment() : FavoriteTvShowFragment
}