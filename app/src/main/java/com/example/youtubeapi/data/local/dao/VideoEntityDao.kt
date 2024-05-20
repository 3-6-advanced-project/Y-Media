package com.example.youtubeapi.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.youtubeapi.data.model.entity.VideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoEntityDao {
    @Query("SELECT * FROM VideoEntity")
    fun getAllVideoEntity(): Flow<List<VideoEntity>>
    @Query("DELETE FROM VideoEntity WHERE videoId = :videoId")
    fun deleteVideoEntityById(videoId: String)
    @Query("INSERT INTO VideoEntity(videoId, title, description, channelTitle, " +
            "channelId, publishedAt, duration, thumbnailUrl) " +
            "VALUES(:videoId, :title, :description, :channelTitle, " +
            ":channelId, :publishedAt, :duration, :thumbnailUrl)")
    fun insertVideoEntityWithParameters(
        videoId: String,
        title: String,
        description: String,
        channelTitle: String,
        channelId: String,
        publishedAt: String,
        duration: String,
        thumbnailUrl: String
    ): Long
}