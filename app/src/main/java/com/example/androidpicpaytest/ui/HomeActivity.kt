package com.example.androidpicpaytest.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidpicpaytest.R
import com.example.androidpicpaytest.common.isNetworkConnect
import com.example.androidpicpaytest.data.network.ContactsResponse
import com.example.androidpicpaytest.data.resource.State
import com.example.androidpicpaytest.data.resource.Status
import com.example.androidpicpaytest.databinding.ActivityHomeBinding
import com.example.androidpicpaytest.ui.adapter.ContactsListAdapter
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }
    private val homeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val contactsListAdapter by lazy { ContactsListAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setObservers()
        viewModel.loadContactsData(this)
    }

    private fun setObservers(){
       viewModel.contactsListDataResponse.observe(this){
           setUpRecyclerView(it)
           homeBinding.userListProgressBar.visibility = View.INVISIBLE
       }
        viewModel.contactsListDataErrorResponse.observe(this){
            Toast.makeText(this, "Erro de Conexão", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpRecyclerView(list: List<ContactsResponse>){
        homeBinding.recyclerView.apply {
            adapter = contactsListAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
        }
        contactsListAdapter.populateAdapter(list)
    }
}