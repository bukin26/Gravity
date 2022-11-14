package com.example.gravity.data

import com.google.gson.annotations.SerializedName

data class LinksDTO(

    @field:SerializedName("link")
    val link: String?,

    @field:SerializedName("home")
    val home: String?
)
