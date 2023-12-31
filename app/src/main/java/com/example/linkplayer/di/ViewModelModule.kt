package com.example.linkplayer.di

import com.example.linkplayer.view_model.TrackViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
//import org.koin.android.viewmodel.dsl.viewModel
//import org.koin.android.viewmodel.compat.ViewModelCompat.viewModel
//import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        TrackViewModel(get(),get())
    }
}