package com.example.zeldabotw_tracker.data.api.model

import com.google.gson.annotations.SerializedName

data class Monster(
    val name: String? = null,
    val id: Int? = null,
    val category: String? = null,
    val description: String? = null,
    @SerializedName( value = "image" )
    val image: String? = null,
    val common_locations: List<String>? = null,
    val drops: List<String>? = null,
    val dlc: Boolean? = null
)