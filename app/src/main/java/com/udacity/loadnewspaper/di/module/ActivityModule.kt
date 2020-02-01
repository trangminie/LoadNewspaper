package com.udacity.loadnewspaper.di.module

import com.udacity.loadnewspaper.ui.activity.DetailActivity
import com.udacity.loadnewspaper.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun provideMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun provideDetailActivity() : DetailActivity
}