package com.example.zeldabotw_tracker.data.api.repository

import com.example.zeldabotw_tracker.data.api.remote.ZeldaApiService

class ZeldaRepository (
    private val api: ZeldaApiService
){
    suspend fun getMonsters() =
        api.getMonsters().data

    suspend fun getFoods() =
        api.getFoods().data

    suspend fun getEquipments() =
        api.getEquipment().data
}