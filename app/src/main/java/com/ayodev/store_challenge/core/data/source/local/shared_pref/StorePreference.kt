package com.ayodev.store_challenge.core.data.source.local.shared_pref

import android.content.Context
import android.content.SharedPreferences
import com.ayodev.store_challenge.core.util.Constants.LOGIN_STATUS
import com.ayodev.store_challenge.core.util.Constants.PREFERENCE_NAME
import com.ayodev.store_challenge.core.util.Constants.USERNAME
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorePreference @Inject constructor(@ApplicationContext context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    private val editor = pref.edit()

    fun setUsername(string: String) {
        editor.putString(USERNAME, string)
        editor.apply()
    }

    fun getUsername(): String? =
        pref.getString(USERNAME, "")

    fun setLoginStatus(status: Boolean) {
        editor.putBoolean(LOGIN_STATUS, status)
        editor.apply()
    }

    fun getLoginStatus(): Boolean =
        pref.getBoolean(LOGIN_STATUS, false)

}