//package com.example.musicapp.model
//
//
//import androidx.room.Database
//import androidx.room.RoomDatabase
//import androidx.room.TypeConverters
//import com.example.linkplayer.model.dao.TrackDao
//import com.example.musicapp.model.Track
//
//@Database(
//    entities = [Track::class],
//    version = 1, exportSchema = false
//)
//
//@TypeConverters(
//    Converters::class)
//abstract class TracksDatabase : RoomDatabase() {
//    abstract val trackDao: TrackDao
//}