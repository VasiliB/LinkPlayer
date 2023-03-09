package com.example.linkplayer.model.api

import com.example.musicapp.model.Track
import retrofit2.Response

interface ApiHelper {

    suspend fun getTracks(): Response<List<Track>>
}