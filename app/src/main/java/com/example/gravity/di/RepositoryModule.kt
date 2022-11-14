package com.example.gravity.di

import com.example.gravity.repository.Repository
import com.example.gravity.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideRepository(repositoryImpl: RepositoryImpl): Repository
}