package com.example.androidpicpaytest.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidpicpaytest.R
import com.example.androidpicpaytest.data.network.ContactsResponse
import com.example.androidpicpaytest.databinding.ActivityHomeBinding
import com.example.androidpicpaytest.ui.adapter.ContactsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel : HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    private val contactsListAdapter = ContactsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()

        setObservers()
        viewModel.loadContactsData(this)
    }

    private fun setObservers(){
       viewModel.contactsListDataResponse.observe(this){ contactsListResponse ->
           contactsListAdapter.populateAdapter(contactsListResponse)
           binding.userListProgressBar.visibility = View.GONE
       }
        viewModel.contactsListDataErrorResponse.observe(this){
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpRecyclerView(){
        val recyclerContactsList = binding.recyclerView
        recyclerContactsList.adapter = contactsListAdapter
        recyclerContactsList.layoutManager = LinearLayoutManager(this)
    }
}