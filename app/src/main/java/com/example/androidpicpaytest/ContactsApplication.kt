package com.example.androidpicpaytest

import android.app.Application
import com.example.androidpicpaytest.di.ApplicationComponent

class ContactsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

       applicationComponent = Dagger
    }
}