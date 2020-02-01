package com.udacity.loadnewspaper.data.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceHelper @Inject constructor(context: Context){
    private var mPref: SharedPreferences = context.getSharedPreferences("LoadNewspaper",
        Context.MODE_PRIVATE)

    fun setNightMode(nightMode: Boolean) {
        mPref.edit().putBoolean(IS_NIGHT_MODE, nightMode).apply()
    }

    fun getNightMode(): Boolean {
        return mPref.getBoolean(IS_NIGHT_MODE, false) ?: false
    }

    companion object {
        const val IS_NIGHT_MODE = "IS_NIGHT_MODE"
    }
}