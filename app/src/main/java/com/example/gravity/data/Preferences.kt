package com.example.gravity.data

import android.content.Context
import com.example.gravity.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

interface Preferences {

    fun getHomeLink(): String?

    fun saveHomeLink(link: String?)
}

@Singleton
class PreferencesImpl @Inject constructor(@ApplicationContext context: Context) : Preferences {

    private val prefs by lazy {
        context.getSharedPreferences("link", Context.MODE_PRIVATE)
    }

    override fun getHomeLink(): String? {
        return prefs.getString(Constants.KEY_HOME_LINK, "")
    }

    override fun saveHomeLink(link: String?) {
        prefs.edit()
            .putString(Constants.KEY_HOME_LINK, link)
            .apply()
    }

}