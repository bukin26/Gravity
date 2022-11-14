package com.example.gravity.di

import com.example.gravity.data.Preferences
import com.example.gravity.data.PreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface  PreferencesModule {

    @Binds
    @Singleton
    fun providePreferences(preferencesImpl: PreferencesImpl): Preferences
}