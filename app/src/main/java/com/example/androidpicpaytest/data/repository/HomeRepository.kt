package com.example.androidpicpaytest.data.repository

import android.content.Context
import com.example.androidpicpaytest.data.network.ContactsResponse
import com.example.androidpicpaytest.data.network.ContactsService
import com.example.androidpicpaytest.data.cache.ContactsDataBase
import com.example.androidpicpaytest.domain.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(val context: Context,
    private val contactsService: ContactsService
): IHomeRepository {

    private fun contactsDB(context: Context): ContactsDataBase{
        return ContactsDataBase.getDataBase(context)
    }

    override suspend fun getListContacts(): Flow<Response<List<ContactsResponse>>> {
        return flow {
            val apiService = contactsService.getContactsList()
            emit(apiService)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getListContactsDatabase(): Flow<List<UserEntity>> {
        return withContext(Dispatchers.IO) {
            contactsDB(context).recipeDao().getContactsListDataBase()
        }
    }

    override suspend fun setListContactsDatabase(userEntityList: List<UserEntity>) {
        withContext(Dispatchers.IO) {
            contactsDB(context).recipeDao().setContactsListDataBase(userEntityList = userEntityList)
        }
    }


}