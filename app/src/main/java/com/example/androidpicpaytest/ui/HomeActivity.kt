package com.example.androidpicpaytest.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidpicpaytest.data.network.ContactsResponse
import com.example.androidpicpaytest.databinding.ActivityHomeBinding
import com.example.androidpicpaytest.ui.adapter.ContactsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel : HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    private val contactsListAdapter = ContactsListAdapter(listOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.loadContactsData(this)
        setObservers()
    }

    private fun setObservers(){
       viewModel.contactsListDataResponse.observe(this){
           setUpRecyclerView()
           contactsListAdapter.populateAdapter(it)
//           homeBinding.userListProgressBar.visibility = View.INVISIBLE
       }
        viewModel.contactsListDataErrorResponse.observe(this){
            Toast.makeText(this, "Erro de Conexão", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpRecyclerView(){
        val recyclerContactsList = binding.recyclerView
        recyclerContactsList.adapter = contactsListAdapter
        recyclerContactsList.layoutManager = LinearLayoutManager(this)

    }
}