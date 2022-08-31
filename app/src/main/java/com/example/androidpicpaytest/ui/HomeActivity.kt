package com.example.androidpicpaytest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpicpaytest.R
import com.example.androidpicpaytest.databinding.ActivityHomeBinding
import com.example.androidpicpaytest.ui.adapter.ContactsListAdapter

class HomeActivity : AppCompatActivity() {

    private val homeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val contactsListAdapter by lazy { ContactsListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}