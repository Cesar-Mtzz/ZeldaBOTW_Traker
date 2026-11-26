package com.example.zeldabotw_tracker.data.api.remote

import com.example.zeldabotw_tracker.data.api.model.ApiResponse
import com.example.zeldabotw_tracker.data.api.model.Equipment
import com.example.zeldabotw_tracker.data.api.model.Food
import com.example.zeldabotw_tracker.data.api.model.Monster
import retrofit2.http.GET

interface ZeldaApiService {
    @GET("category/monsters")
    suspend fun getMonsters(): ApiResponse<List<Monster>>

    @GET("category/materials")
    suspend fun getFoods(): ApiResponse<List<Food>>

    @GET("category/equipment")
    suspend fun getEquipment(): ApiResponse<List<Equipment>>
}