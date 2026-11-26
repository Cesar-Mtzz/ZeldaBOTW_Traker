package com.example.zeldabotw_tracker.data.api.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{
    private const val BASE_URL = "https://api.hyrule-compendium.com/v3/compendium/"
    val api: ZeldaApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ZeldaApiService::class.java)
    }
}