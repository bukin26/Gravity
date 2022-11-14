package com.example.gravity.repository

import com.example.gravity.data.LinksService
import com.example.gravity.data.Preferences
import com.example.gravity.data.wrapResult
import javax.inject.Inject
import javax.inject.Singleton
import com.example.gravity.data.Result

interface Repository {

    suspend fun getLinksFromApi(): Result<String>
}

@Singleton
class RepositoryImpl @Inject constructor(
    private val service: LinksService,
    private val preferences: Preferences
) : Repository {

    override suspend fun getLinksFromApi(): Result<String> {
        return wrapResult {
            val response = service.fetchLinks()
            if (!response.isSuccessful || response.body()?.link.isNullOrEmpty()) throw RuntimeException()
            response.body()?.let {
                if (!it.home.isNullOrEmpty()) {
                    preferences.saveHomeLink(it.home)
                }
            }
            return@wrapResult response.body()?.link ?: throw RuntimeException()
        }
    }
}