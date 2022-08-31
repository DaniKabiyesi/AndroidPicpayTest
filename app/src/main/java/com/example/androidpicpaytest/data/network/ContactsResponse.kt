package com.example.androidpicpaytest.data.network

import com.google.gson.annotations.SerializedName

data class ContactsResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("img")
    val img: String?,

    @SerializedName("username")
    val username: String?


)
