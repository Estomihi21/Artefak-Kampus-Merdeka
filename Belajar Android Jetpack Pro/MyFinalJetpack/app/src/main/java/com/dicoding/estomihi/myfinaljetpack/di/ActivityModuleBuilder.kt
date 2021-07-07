package com.dicoding.estomihi.myfinaljetpack.di

import com.dicoding.estomihi.myfinaljetpack.di.favorite.FavoriteFragmentBuildersModule
import com.dicoding.estomihi.myfinaljetpack.di.main.MainFragmentBuildersModule
import com.dicoding.estomihi.myfinaljetpack.detail.DetailActivity
import com.dicoding.estomihi.myfinaljetpack.favorite.FavoriteActivity
import com.dicoding.estomihi.myfinaljetpack.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModuleBuilder {

    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FavoriteFragmentBuildersModule::class])
    abstract fun contributeFavoriteActivity():FavoriteActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity

}