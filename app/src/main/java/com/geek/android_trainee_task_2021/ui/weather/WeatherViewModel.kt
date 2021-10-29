package com.geek.android_trainee_task_2021.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geek.android_trainee_task_2021.data.repository.RemoteRepositoryImpl
import com.geek.android_trainee_task_2021.domain.model.Latlng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val remoteRepo: RemoteRepositoryImpl) :
    ViewModel() {

    val progressBar = MutableLiveData<Boolean>()

    fun loadCurrentWeather(cityName: String) =
        remoteRepo.getCurrentWeather(cityName)

    fun loadDailyWeather(latlng: Latlng) =
        remoteRepo.getDailyWeather(latlng)

}