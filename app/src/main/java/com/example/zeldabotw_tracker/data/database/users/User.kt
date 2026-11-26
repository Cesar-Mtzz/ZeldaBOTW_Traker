package com.example.zeldabotw_tracker.data.database.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userName: String,
    val password: String,
    val name: String
)