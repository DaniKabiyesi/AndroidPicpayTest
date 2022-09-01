package com.example.androidpicpaytest.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ContactsRetrofit {

    companion object {
        private val retrofitService: ContactsService by lazy {
            val retrofit=
                Retrofit.Builder()
                    .client(okHttp)
                    .baseUrl("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api")
                    .addConverterFactory(GsonConverterFactory.create()).build()

            retrofit.create(ContactsService::class.java)
        }

        private val okHttp: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .build()
        }

        fun getInstance(): ContactsService{
            return retrofitService
        }
    }
}