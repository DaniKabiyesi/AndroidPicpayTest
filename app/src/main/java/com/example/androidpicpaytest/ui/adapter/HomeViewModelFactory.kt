package com.example.androidpicpaytest.ui.adapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidpicpaytest.data.network.ContactsRetrofit
import com.example.androidpicpaytest.data.repository.HomeRepository
import com.example.androidpicpaytest.ui.HomeViewModel
import java.lang.IllegalArgumentException

class HomeViewModelFactory() : ViewModelProvider.Factory {

    private val retrofitService = ContactsRetrofit.getInstance()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            val repository = HomeRepository(retrofitService)
            HomeViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}