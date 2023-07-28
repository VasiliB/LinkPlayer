package com.example.linkplayer.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.linkplayer.model.dao.TrackDao
import com.example.musicapp.model.Converters

@Database(entities = [Track::class], version = 1, exportSchema = false)

@TypeConverters(
    Converters::class
)

abstract class AppDatabase : RoomDatabase() {
    abstract val trackDao: TrackDao
}