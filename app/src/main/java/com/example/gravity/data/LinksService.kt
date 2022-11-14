package com.example.gravity.data

import retrofit2.Response
import retrofit2.http.GET

interface LinksService {

    @GET("prod")
    suspend fun fetchLinks(): Response<LinksDTO>
}