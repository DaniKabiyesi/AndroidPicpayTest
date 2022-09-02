package com.example.androidpicpaytest.ui.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.androidpicpaytest.R
import com.example.androidpicpaytest.common.Utils
import com.example.androidpicpaytest.data.network.ContactsResponse
import timber.log.Timber

class ContactsListAdapter(private val listContatcs: List<ContactsResponse>) : RecyclerView.Adapter<ContactsListViewHolder>() {

    private var contactsAdapterList: List<ContactsResponse> = listOf()

    override fun getItemCount(): Int {
        return contactsAdapterList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_contacts_list, parent, false)
        return ContactsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsListViewHolder, position: Int) {
        val entity = contactsAdapterList[position]
        bindItemViewHolder(holder, entity)
    }

    private fun bindItemViewHolder(holder: ContactsListViewHolder, model: ContactsResponse) {
        holder.username.text = model.username
        holder.name.text = model.name

        try {
            Glide.with(holder.itemView)
                .asBitmap()
                .load(model.img)
                .placeholder(R.drawable.ic_profile_empty)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .circleCrop().listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Timber.tag(Utils.TAG_TIMBER).v("Exception: ${e?.message.toString()}")
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                }).into(holder.profileImage)
        } catch (e: Exception) {
            Timber.tag(Utils.TAG_TIMBER).v("Exception: ${e.message.toString()}")
        }

    }

    fun populateAdapter(contactsList: List<ContactsResponse>) {
        this.contactsAdapterList = contactsList
        notifyItemRangeInserted(0, contactsList.size)
    }
}