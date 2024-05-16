package com.example.youtubeapi.network

import com.example.youtubeapi.GOOGLE_API_URL_BASE
import com.example.youtubeapi.GOOGLE_API_URL_DEVELOP
import com.example.youtubeapi.data.remote.RemoteDataSource
import com.example.youtubeapi.data.remote.SearchDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    fun buildRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val base: SearchDataSource by lazy {
        buildRetrofit(GOOGLE_API_URL_BASE).create(SearchDataSource::class.java)
    }

    val develop: RemoteDataSource by lazy {
        buildRetrofit(GOOGLE_API_URL_DEVELOP).create(RemoteDataSource::class.java)
    }
}

/**
 *
 *
 * Youtube Video List api
 *      GET https://developers.google.com/youtube/v3/docs/videos/list?hl=ko
 *
 * Youtube Video Category api
 *      GET https://www.googleapis.com/youtube/v3/videoCategories
 *
 *
 * */