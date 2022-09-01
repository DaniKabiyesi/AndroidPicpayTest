package com.example.androidpicpaytest.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpicpaytest.common.Utils
import com.example.androidpicpaytest.common.isNetworkConnect
import com.example.androidpicpaytest.data.network.ContactsResponse
import com.example.androidpicpaytest.data.repository.IHomeRepository
import com.example.androidpicpaytest.data.resource.State
import com.example.androidpicpaytest.domain.UserEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: IHomeRepository) : ViewModel() {

    private val _contactsListDataResponse = MutableLiveData<List<ContactsResponse>>()
    val contactsListDataResponse: LiveData<List<ContactsResponse>> get() = _contactsListDataResponse

    private val _contactsListDataErrorResponse = MutableLiveData<String>()
    val contactsListDataErrorResponse: LiveData<String> get() = _contactsListDataErrorResponse

    fun loadContactsData(context: Context, ) {
        if(isNetworkConnect(context = context)){
            getContactsListNetwork()
        } else {
            getContactsListDatabase()
        }
    }

    fun setContactsListDatabase(userEntityList: List<UserEntity>) {
        viewModelScope.launch {
            repository.setListContactsDatabase(userEntityList)
        }
    }

    fun getContactsListDatabase() = viewModelScope.launch {
//        _contactsListDataResponse.postValue(State.loading(null))
        repository.getListContactsDatabase().collect{ userEntityList ->
            if(userEntityList.isEmpty()){
                _contactsListDataErrorResponse.postValue(Utils.ERROR_LIST)
            } else {
                val contactsResponseList = userEntityList.map {
                    ContactsResponse(
                        id = it.id,
                        name = it.name,
                        img = it.img,
                        username = it.username
                    )
                }
                _contactsListDataResponse.postValue(contactsResponseList)
            }
        }
    }

    fun getContactsListNetwork() = viewModelScope.launch {
//        _contactsListDataResponse.postValue(State.loading(data = null))
        repository.getListContacts().collect{ response ->
            if(response.isSuccessful){
                response.body()?.let { contactsListResponse ->
                    val userEntityList = contactsListResponse.map {
                        UserEntity(
                            id = it.id,
                            name = it.name,
                            img = it.img,
                            username = it.username
                        )
                    }
                    _contactsListDataResponse.postValue(contactsListResponse)
                    setContactsListDatabase(userEntityList)
                }
            } else {
                _contactsListDataErrorResponse.postValue("${response.message()} ${response.code()}")
            }
        }
    }
}