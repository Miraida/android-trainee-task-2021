package com.geek.android_trainee_task_2021.utils

import android.content.Context
import android.content.SharedPreferences
import com.geek.android_trainee_task_2021.common.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultSharedPref @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(Constants.pref_key, Context.MODE_PRIVATE)

    fun getStoredCityName(): String {
        return prefs.getString(Constants.city_name_key, Constants.def_city_name)!!
    }

    fun setStoredCityName(city_name: String) {
        prefs.edit().putString(Constants.city_name_key, city_name).apply()
    }

    fun clearStoredCityName() {
        setStoredCityName("")
    }
}