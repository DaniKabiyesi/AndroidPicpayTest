package com.example.androidpicpaytest.ui.di

import androidx.lifecycle.ViewModel
import com.example.androidpicpaytest.di.ViewModelKey
import com.example.androidpicpaytest.ui.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}