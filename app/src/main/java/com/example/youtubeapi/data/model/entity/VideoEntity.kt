package com.example.youtubeapi.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.youtubeapi.data.model.dto.Thumbnail

// TODO: duration 반영 필요

@Entity
data class VideoEntity(
    @PrimaryKey(autoGenerate = true) val rowId: Long,
    @ColumnInfo(defaultValue = "")
    val kind: String,
    @ColumnInfo(defaultValue = "")
    val etag: String,
    val videoId: String,
    val title: String,
    val description: String,
    val channelTitle: String,
    val channelId: String,
    val publishedAt: String,
    @ColumnInfo(defaultValue = "")
    val publishTime: String,
    val duration: String,
    val thumbnailUrl: String,
)