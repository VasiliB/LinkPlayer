package com.example.linkplayer.di

import com.example.linkplayer.domain.GetTracksUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetTracksUseCase(get(), get()) }
}