package com.example.zeldabotw_tracker.data.database.users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {
    @Insert
    suspend fun addUser(user: User): Long

    @Query("SELECT id FROM users WHERE userName = :userName LIMIT 1")
    suspend fun getUserId(userName: String): Int?

    @Query("SELECT password FROM users WHERE userName = :userName LIMIT 1")
    suspend fun login(userName: String): String?
}