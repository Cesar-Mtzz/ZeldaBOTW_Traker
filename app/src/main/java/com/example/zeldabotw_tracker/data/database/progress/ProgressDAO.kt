package com.example.zeldabotw_tracker.data.database.progress

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {
    @Query("SELECT * FROM user_progress WHERE userId = :userId")
    fun getProgressByUser(userId: Int): Flow<Progress?>

    @Query("SELECT * FROM user_progress WHERE userId = :userId LIMIT 1")
    suspend fun getProgressDirect(userId: Int): Progress?

    @Upsert
    suspend fun saveProgress(progress: Progress)
}
