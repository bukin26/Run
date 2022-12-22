package com.gmail.bukin26.mg.data

import com.gmail.bukin26.mg.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getNews(
        @Query("category") category: String = "sports",
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    )
}