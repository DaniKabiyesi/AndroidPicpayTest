package com.example.androidpicpaytest.data.repository

import android.content.Context
import com.example.androidpicpaytest.data.network.ContactsResponse
import com.example.androidpicpaytest.domain.UserEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IHomeRepository {

    suspend fun getListContacts(): Flow<Response<List<ContactsResponse>>>

    suspend fun getListContactsDatabase(context: Context): Flow<List<UserEntity>>

    suspend fun setListContactsDatabase(context: Context, userEntityList: List<UserEntity>)
}