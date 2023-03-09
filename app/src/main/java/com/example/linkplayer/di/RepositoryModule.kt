package com.example.linkplayer.di

import com.example.linkplayer.model.repository.TrackRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        TrackRepository(get())
    }
}