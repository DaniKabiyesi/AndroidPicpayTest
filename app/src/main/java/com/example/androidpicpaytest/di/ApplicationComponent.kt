package com.example.androidpicpaytest.di

import android.content.Context
import com.example.androidpicpaytest.ui.di.HomeComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, ViewModelBuilderModule::class, SubcomponentsModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance applicationContext: Context) : ApplicationComponent
    }

    fun homeComponent(): HomeComponent.Factory
}

@Module(subcomponents = [HomeComponent::class])
object SubcomponentsModule