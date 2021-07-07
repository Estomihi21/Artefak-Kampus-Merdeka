package com.dicoding.estomihi.myfinaljetpack.di

import android.app.Application
import com.dicoding.estomihi.myfinaljetpack.source.ContentRepository
import com.dicoding.estomihi.myfinaljetpack.source.local.LocalDataSource
import com.dicoding.estomihi.myfinaljetpack.source.local.room.ContentDao
import com.dicoding.estomihi.myfinaljetpack.source.local.room.ContentDatabase
import com.dicoding.estomihi.myfinaljetpack.source.remote.RemoteDataSource
import com.dicoding.estomihi.myfinaljetpack.source.remote.api.ApiService
import com.dicoding.estomihi.myfinaljetpack.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideContentDatabase(application: Application): ContentDatabase =
            ContentDatabase.getInstance(application)

        @Singleton
        @Provides
        fun provideContentDao(contentDatabase: ContentDatabase): ContentDao =
            contentDatabase.contentDao()

        @Singleton
        @Provides
        fun provideLocalDataSource(contentDao: ContentDao): LocalDataSource =
            LocalDataSource(contentDao)

        @Singleton
        @Provides
        fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource =
            RemoteDataSource(apiService)

        @Singleton
        @Provides
        fun provideContentRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): ContentRepository = ContentRepository(remoteDataSource, localDataSource)

        @Singleton
        @Provides
        fun provideViewModelFactory(contentRepository: ContentRepository): ViewModelFactory =
            ViewModelFactory(contentRepository)

    }
}