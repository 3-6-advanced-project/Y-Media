package com.example.youtubeapi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.youtubeapi.data.local.dao.VideoDao
import com.example.youtubeapi.data.model.entity.VideoEntity

@Database(entities = [VideoEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun videoDao(): VideoDao
    companion object {
        private var INSTANCE: AppDatabase? = null
        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_db"
                ).build()
            }
            return INSTANCE
        }
    }
}