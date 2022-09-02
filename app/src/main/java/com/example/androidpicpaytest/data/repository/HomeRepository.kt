package com.example.androidpicpaytest.data.repository

import android.content.Context
import com.example.androidpicpaytest.data.db.ContactsDataBase
import com.example.androidpicpaytest.data.network.ContactsResponse
import com.example.androidpicpaytest.data.network.ContactsService
import com.example.androidpicpaytest.domain.UserEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val contactsService: ContactsService
): IHomeRepository {

    private fun contactsDB(): ContactsDataBase{
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
            contactsDB().getUserDao().getContactsListDataBase()
        }
    }

    override suspend fun setListContactsDatabase(userEntityList: List<UserEntity>) {
        withContext(Dispatchers.IO) {
            contactsDB().getUserDao().setContactsListDataBase(userEntityList = userEntityList)
        }
    }


}