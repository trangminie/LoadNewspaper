package com.udacity.loadnewspaper.di.module

import com.udacity.loadnewspaper.ui.fragment.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun provideDetailFragment() : DetailFragment
}