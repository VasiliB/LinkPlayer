package com.example.linkplayer.view.track_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linkplayer.domain.GetTracksResult
import com.example.linkplayer.domain.GetTracksUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class TrackViewModel(private val getTrackUseCase: GetTracksUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(TrackListUiState())
    val uiState: StateFlow<TrackListUiState> = _uiState.asStateFlow()

    private val _errorEvent = Channel<Boolean>()
    val errorEvent = _errorEvent.receiveAsFlow()

    init {
        fetchTracks()
    }

    private fun fetchTracks() {
        viewModelScope.launch {
            _uiState.update { it.copy(screenState = ScreenState.LOADING, errorText = "") }
            when (val result = getTrackUseCase()) {
                is GetTracksResult.Error -> {
                    _uiState.update {
                        it.copy(screenState = ScreenState.ERROR, errorText = result.error)
                    }
                    _errorEvent.send(true)
                }
                is GetTracksResult.Success -> {
                    _uiState.update {
                        it.copy(screenState = ScreenState.IDLE, tracks = result.tracks)
                    }
                }
            }
        }
    }
}