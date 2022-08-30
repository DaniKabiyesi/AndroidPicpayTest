package com.example.androidpicpaytest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpicpaytest.R
import com.example.androidpicpaytest.domain.network.repository.ContactsResponse

class ContactsListAdapter(private val listContacts: List<ContactsResponse>) : RecyclerView.Adapter<ContactsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_contacts_list, parent, false)
        return ContactsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsListViewHolder, position: Int) {
        when(holder){
            is ContactsListViewHolder -> {
                holder.bind(listContacts[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return listContacts.size
    }
}