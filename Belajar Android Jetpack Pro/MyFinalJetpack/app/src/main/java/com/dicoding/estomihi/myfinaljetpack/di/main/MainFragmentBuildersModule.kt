package com.dicoding.estomihi.myfinaljetpack.di.main

import com.dicoding.estomihi.myfinaljetpack.home.movie.MovieFragment
import com.dicoding.estomihi.myfinaljetpack.home.tvshow.TvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment() : MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment() : TvShowFragment

}