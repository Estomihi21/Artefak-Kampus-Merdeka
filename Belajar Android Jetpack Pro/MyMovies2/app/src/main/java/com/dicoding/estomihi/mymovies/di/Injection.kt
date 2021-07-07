package com.dicoding.estomihi.mymovies.di

import android.content.Context
import com.dicoding.estomihi.mymovies.data.source.ContentRepository
import com.dicoding.estomihi.mymovies.data.source.remote.RemoteDataSource

object Injection {

    fun provideRepository(context: Context): ContentRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return ContentRepository.getInstance(remoteDataSource)
    }
}