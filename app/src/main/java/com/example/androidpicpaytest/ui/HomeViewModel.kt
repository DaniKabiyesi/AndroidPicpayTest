package com.example.androidpicpaytest.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpicpaytest.common.Utils
import com.example.androidpicpaytest.data.network.ContactsResponse
import com.example.androidpicpaytest.data.repository.IHomeRepository
import com.example.androidpicpaytest.data.resource.State
import com.example.androidpicpaytest.domain.UserEntity
import dagger.internal.DaggerGenerated
import kotlinx.coroutines.launch
import javax.inject.Inject

@DaggerGenerated
class HomeViewModel @Inject constructor(private val repository: IHomeRepository) : ViewModel() {

    private val _contactsListDataResponse = MutableLiveData<State<List<ContactsResponse>>>()
    val contactsListDataResponse: LiveData<State<List<ContactsResponse>>> get() = _contactsListDataResponse

    private val _contactsListDataBase = MutableLiveData<State<List<UserEntity>>>()
    val contactsListDataBase: LiveData<State<List<UserEntity>>> get() = _contactsListDataBase

    private val _contactsListDataErrorResponse = MutableLiveData<String>()
    val contactsListDataErrorResponse: LiveData<String> get() = _contactsListDataErrorResponse



//    fun loadContactsData(){
//        if(isNetworkConnect)
//            //TODO loadContactsData = Carregar dados
//    }



    fun setContactsListDatabase(context: Context, userEntityList: List<UserEntity>) {
        viewModelScope.launch {
            repository.setListContactsDatabase(context, userEntityList)
        }
    }

    fun getContactsListDatabase(context: Context) = viewModelScope.launch {
        _contactsListDataBase.postValue(State.loading(null))
        repository.getListContactsDatabase(context).collect{ userEntityList ->
            if(userEntityList.isEmpty()){
                _contactsListDataBase.postValue(
                    State.error(
                        data = null,
                        message = Utils.ERROR_LIST
                    )
                )
                //TODO messageLog
            } else{
                val contactsResponseList = userEntityList.map {
                    UserEntity(
                        id = it.id,
                        name = it.name,
                        img = it.img,
                        username = it.username
                    )
                }
                _contactsListDataBase.postValue(State.success(data = contactsResponseList))
            }
        }
    }

    fun getContactsListNetwork(){

    }
}