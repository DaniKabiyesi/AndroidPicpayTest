package com.example.androidpicpaytest.ui.di

import com.example.androidpicpaytest.ui.HomeActivity
import dagger.Subcomponent

@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(activity: HomeActivity)
}