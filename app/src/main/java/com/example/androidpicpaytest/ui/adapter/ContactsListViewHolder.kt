package com.example.androidpicpaytest.ui.adapter

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.androidpicpaytest.R
import com.example.androidpicpaytest.domain.network.repository.ContactsResponse
import com.example.androidpicpaytest.utils.Utils
import timber.log.Timber

class ContactsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val username: TextView=itemView.findViewById(R.id.usernameTextView)
    private val name: TextView=itemView.findViewById(R.id.nameTextView)
    private val profileImage: ImageView=itemView.findViewById(R.id.profileImageView)

    fun bind(model: ContactsResponse) {
        username.text = model.username
        name.text = model.name

        try {
            Glide.with(itemView)
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
                }).into(profileImage)
        } catch (e: Exception) {
            Timber.tag(Utils.TAG_TIMBER).v("Exception: ${e.message.toString()}")
        }
    }
}