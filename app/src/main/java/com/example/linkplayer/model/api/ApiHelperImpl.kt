package com.example.linkplayer.model.api

import com.example.linkplayer.model.Track
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getTracks(): Response<List<Track>> = apiService.getUsers()

}