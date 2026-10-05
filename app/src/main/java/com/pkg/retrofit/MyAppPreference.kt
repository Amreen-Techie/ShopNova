package com.pkg.retrofit

import android.content.Context
import android.content.SharedPreferences

class MyAppPreference (val context: Context) {

    val PREF_NAME = "my_app_pref"
    val KEY_INFO = "key_info"
    var sharedPreference: SharedPreferences

    init{
        sharedPreference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    //To save information
    fun saveInfoToken(info: String)
    {
        var editor = sharedPreference.edit()
        editor.putString(KEY_INFO, info)
        editor.commit()
    }

    //To get information
    fun getInfoToken() : String
    {
        var info = sharedPreference.getString(KEY_INFO, "")
        return info ?: ""
    }
    fun getSP_Clear()
    {
        sharedPreference.edit().clear().apply()
    }
}