package com.example.linkplayer.domain

import com.example.linkplayer.model.repository.TrackRepository
import com.example.linkplayer.utils.NetworkHelper
import com.example.musicapp.model.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetTracksUseCase(
    private val trackRepository: TrackRepository,
    private val networkHelper: NetworkHelper
) {
    suspend operator fun invoke(): GetTracksResult = withContext(Dispatchers.IO) {
        if (networkHelper.isNetworkConnected()) {
            val result = trackRepository.getTracks()
            if (result.isSuccessful) {
                GetTracksResult.Success(result.body() ?: emptyList())
            } else {
                GetTracksResult.Error(result.errorBody().toString())
            }
        } else {
            GetTracksResult.Error("No internet connection")
        }
    }
}

sealed class GetTracksResult {
    data class Success(val tracks: List<Track>) : GetTracksResult()
    data class Error(val error: String) : GetTracksResult()
}