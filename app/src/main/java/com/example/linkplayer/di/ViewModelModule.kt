package com.example.linkplayer.di


import com.example.linkplayer.view.track_list.TrackViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        TrackViewModel(get())
    }
}