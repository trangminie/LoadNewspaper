package com.udacity.loadnewspaper

import com.udacity.loadnewspaper.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication

class LoadNewspaperApp : DaggerApplication(), HasActivityInjector{

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

}