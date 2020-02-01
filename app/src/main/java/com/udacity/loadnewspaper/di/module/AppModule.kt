package com.udacity.loadnewspaper.di.module

import android.app.Application
import android.content.Context
import com.udacity.loadnewspaper.LoadNewspaperApp
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideApp(application: LoadNewspaperApp) : Application = application

    @Provides
    fun provideContext(application: LoadNewspaperApp) : Context = application.applicationContext
}