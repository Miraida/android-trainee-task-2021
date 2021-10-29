package com.geek.android_trainee_task_2021.extension

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build

fun Context.checkNetwork(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager.activeNetwork
    } else {
        connectivityManager.activeNetworkInfo
    }

    return if (activeNetworkInfo != null) {
        (true)

    } else {

        (false)
    }
}