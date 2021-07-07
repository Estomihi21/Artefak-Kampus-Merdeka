package com.dicoding.estomihi.myfinaljetpack.di

import android.app.Application
import com.dicoding.estomihi.myfinaljetpack.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModuleBuilder::class,
        AppModule::class,
        NetworkModule::class]
)
interface DaggerAppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): DaggerAppComponent
    }
}