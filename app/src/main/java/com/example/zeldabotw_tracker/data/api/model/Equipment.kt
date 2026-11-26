package com.example.zeldabotw_tracker.data.api.model

import com.google.gson.annotations.SerializedName

data class Equipment(
    val name: String? = null,
    val id: Int? = null,
    val category: String? = null,
    val description: String? = null,
    @SerializedName("image")
    val image: String? = null,
    val common_locations: List<String>? = null,
    val properties: EquipmentProperties? = null,
    val dlc: Boolean? = null
)

data class EquipmentProperties(
    val attack: Int? = null,
    val defense: Int? = null
)