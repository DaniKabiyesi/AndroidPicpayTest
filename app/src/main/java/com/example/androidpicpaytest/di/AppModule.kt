package com.example.androidpicpaytest.di

import android.content.Context
import com.example.androidpicpaytest.BuildConfig
import com.example.androidpicpaytest.data.db.ContactsDataBase
import com.example.androidpicpaytest.data.db.UserDao
import com.example.androidpicpaytest.data.network.ContactsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getContactsDataBase(context: Context): ContactsDataBase{
        return ContactsDataBase.getDataBase(context)
    }

    @Provides
    @Singleton
    fun getUserDao(contactsDataBase: ContactsDataBase): UserDao{
        return contactsDataBase.getUserDao()
    }


    @Provides
    @Singleton
    fun getRetrofitInstance(retrofit: Retrofit) : ContactsService{
        return retrofit.create(ContactsService::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}