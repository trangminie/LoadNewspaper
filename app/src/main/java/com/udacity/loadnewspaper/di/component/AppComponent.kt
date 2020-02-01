package com.udacity.loadnewspaper.di.component

import com.udacity.loadnewspaper.LoadNewspaperApp
import com.udacity.loadnewspaper.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityModule::class,
    RepositoryModule::class,
    ApiModule::class,
    FragmentModule::class
])
@Singleton
interface AppComponent : AndroidInjector<LoadNewspaperApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<LoadNewspaperApp>()

    override fun inject(app: LoadNewspaperApp)
}