package com.example.linkplayer.model.repository

import com.example.linkplayer.model.api.ApiHelper

class TrackRepository(private val apiHelper: ApiHelper) {

    suspend fun getTracks() = apiHelper.getTracks()
}


//class TrackRepository(private val trackApi: TrackApi, private val trackDao: TrackDao) {
//
//    val data = trackDao.findAll()
//
//    suspend fun refresh() {
//        withContext(Dispatchers.IO) {
//            val tracks = trackApi.getAllAsync().await()
//            trackDao.add(tracks)
//        }
//    }
//}