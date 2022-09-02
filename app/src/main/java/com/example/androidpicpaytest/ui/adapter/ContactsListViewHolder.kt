package com.example.androidpicpaytest.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpicpaytest.R

class ContactsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     val username: TextView = itemView.findViewById(R.id.usernameTextView)
     val name: TextView = itemView.findViewById(R.id.nameTextView)
     val profileImage: ImageView = itemView.findViewById(R.id.profileImageView)
}