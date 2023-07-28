package com.example.linkplayer.view.track_list

import com.example.linkplayer.model.Track

data class TrackListUiState(
    val screenState: ScreenState = ScreenState.IDLE,
    val tracks: List<Track> = emptyList(),
    val errorText:String = ""
)

enum class ScreenState {
    LOADING, ERROR, IDLE
}