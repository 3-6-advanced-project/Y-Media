package com.example.youtubeapi.network

import com.example.youtubeapi.GOOGLE_API_URL_BASE
import com.example.youtubeapi.GOOGLE_API_URL_DEVELOP
import com.example.youtubeapi.data.remote.DeveloperDataSource
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

    /**
     *
     * retrofit -> retrofitVideo 임의 조정
     *
     * */
    private val retrofitGoogleApi by lazy {
        Retrofit.Builder()
            .baseUrl(GOOGLE_API_URL_BASE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val retrofitDeveloperApi by lazy {
        Retrofit.Builder()
            .baseUrl(GOOGLE_API_URL_DEVELOP)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val googleApiSource: GoogleApiDataSource by lazy {
        retrofitGoogleApi.create(GoogleApiDataSource::class.java)
    }

    val developerApiSource: DeveloperDataSource by lazy {
        retrofitDeveloperApi.create(DeveloperDataSource::class.java)
    }
}

/**
 *
 *
 * Youtube SearchVideo List api
 *      GET https://developers.google.com/youtube/v3/docs/videos/list?hl=ko
 *
 * Youtube SearchVideo Category api
 *      GET https://www.googleapis.com/youtube/v3/videoCategories
 *
 *
 * */