package com.udacity.loadnewspaper.di.module

import com.udacity.loadnewspaper.data.api.RetrofitNewspaperApi
import com.udacity.loadnewspaper.data.repository.NewspaperRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providerNewspaperRepo(newspaperApi: RetrofitNewspaperApi) : NewspaperRepository {
        return NewspaperRepository(newspaperApi)
    }
}