package com.udacity.loadnewspaper.di.module

import com.udacity.loadnewspaper.data.api.RetrofitNewspaperApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun providerNewspaperApi() : RetrofitNewspaperApi = RetrofitNewspaperApi()
}