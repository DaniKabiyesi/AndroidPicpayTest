package com.example.androidpicpaytest.di

import com.example.androidpicpaytest.data.repository.HomeRepository
import com.example.androidpicpaytest.data.repository.IHomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(homeRepository: HomeRepository) : IHomeRepository
}