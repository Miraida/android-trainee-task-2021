package com.geek.android_trainee_task_2021.domain.repository

import androidx.lifecycle.LiveData
import com.geek.android_trainee_task_2021.common.network.Resource
import com.geek.android_trainee_task_2021.domain.model.CurrentWeather
import com.geek.android_trainee_task_2021.domain.model.DailyWeather
import com.geek.android_trainee_task_2021.domain.model.Latlng

interface RemoteRepository {

    fun getCurrentWeather(cityName: String): LiveData<Resource<CurrentWeather>>
    fun getDailyWeather(latlng: Latlng): LiveData<Resource<DailyWeather>>
}