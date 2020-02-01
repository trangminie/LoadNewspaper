package com.udacity.loadnewspaper.data.api

import com.udacity.loadnewspaper.data.entity.RssFeed
import com.udacity.loadnewspaper.data.entity.RssItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET

class RetrofitNewspaperApi : ApiService {

    override fun getRssFeed(): RssFeed {
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://vnexpress.net/rss/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(RssService::class.java).getRssFeed().execute().body()!!
    }

    interface RssService {

        @GET("tin-moi-nhat.rss")
        fun getRssFeed(): Call<RssFeed>
    }
}