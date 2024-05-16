package com.example.youtubeapi.network

import com.example.youtubeapi.data.remote.GoogleApiDataSource
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
    private val retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val search: GoogleApiDataSource by lazy {
        retrofit.create(GoogleApiDataSource::class.java)
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