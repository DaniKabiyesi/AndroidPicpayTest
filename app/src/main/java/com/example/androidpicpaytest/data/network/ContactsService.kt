package com.example.androidpicpaytest.data.network

import retrofit2.Response
import retrofit2.http.GET

interface ContactsService {

    @GET("users")
    suspend fun getContactsList(): Response<List<ContactsResponse>>
}