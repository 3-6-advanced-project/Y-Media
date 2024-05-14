package com.example.youtubeapi.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.youtubeapi.data.model.VideoEntity

@Dao
interface VideoDao {
    @Query("SELECT * FROM VideoEntity")
    fun getAllVideoEntity(): List<VideoEntity>
    @Delete
    fun deleteVideoEntity(videoEntity: VideoEntity)
    @Insert
    fun insertVideoEntity(videoEntity: VideoEntity): Long
}