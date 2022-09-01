package com.example.androidpicpaytest.di

import com.example.androidpicpaytest.data.repository.HomeRepository
import com.example.androidpicpaytest.data.repository.IHomeRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun provideLocalDataSource(repository: HomeRepository): IHomeRepository
}