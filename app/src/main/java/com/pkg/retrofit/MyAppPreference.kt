package com.pkg.retrofit

import android.content.Context
import android.content.SharedPreferences

/**
 * A utility class for managing SharedPreferences within the application,
 * used to securely store and retrieve user session or token information.
 */
class MyAppPreference (val context: Context) {

    val PREF_NAME = "my_app_pref"
    val KEY_INFO = "key_info"
    var sharedPreference: SharedPreferences

    init{
        sharedPreference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Saves the provided information or auth token string into SharedPreferences.
     */
    fun saveInfoToken(info: String)
    {
        var editor = sharedPreference.edit()
        editor.putString(KEY_INFO, info)
        editor.commit()
    }

    /**
     * Retrieves the saved information or auth token string from SharedPreferences.
     * Returns an empty string if no value is found.
     */
    fun getInfoToken() : String
    {
        var info = sharedPreference.getString(KEY_INFO, "")
        return info ?: ""
    }
    /**
     * Clears all stored data from SharedPreferences.
     */
    fun getSP_Clear()
    {
        sharedPreference.edit().clear().apply()
    }
}