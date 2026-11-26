package com.example.zeldabotw_tracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.zeldabotw_tracker.data.database.progress.Progress
import com.example.zeldabotw_tracker.data.database.progress.ProgressDao
import com.example.zeldabotw_tracker.data.database.users.UserDAO
import com.example.zeldabotw_tracker.data.database.users.User

@Database(
    entities = [User::class, Progress::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDAO() : UserDAO
    abstract fun progressDAO() : ProgressDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    klass = AppDatabase::class.java,
                    name = "zelda_tracker_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}