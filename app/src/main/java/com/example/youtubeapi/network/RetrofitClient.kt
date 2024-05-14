package com.example.youtubeapi.network

import com.example.youtubeapi.data.remote.RemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://www.googleapis.com/"
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
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val search: RemoteDataSource by lazy {
        retrofit.create(RemoteDataSource::class.java)
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