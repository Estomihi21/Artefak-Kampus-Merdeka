package com.dicoding.estomihi.myfinaljetpack

import com.dicoding.estomihi.myfinaljetpack.di.DaggerDaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerDaggerAppComponent.builder().application(this).build()
}