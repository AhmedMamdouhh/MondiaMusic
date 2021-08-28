package com.mondia.manager.utilities

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager private constructor() {

    companion object {
        private val sharePref = SharedPreferencesManager()
        private lateinit var sharedPreferences: SharedPreferences


        fun getInstance(context: Context): SharedPreferencesManager {
            if (!::sharedPreferences.isInitialized) {
                synchronized(SharedPreferencesManager::class.java) {
                    if (!::sharedPreferences.isInitialized) {
                        sharedPreferences = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
                    }
                }
            }
            return sharePref
        }
    }



    fun savePlaceObj(placeObjStr: String,stringKey:String) {
        sharedPreferences.edit()
            .putString(stringKey, placeObjStr)
            .apply()
    }

    fun getSavedData(stringKey: String): String? {
        return sharedPreferences.getString(stringKey, "")
    }



}