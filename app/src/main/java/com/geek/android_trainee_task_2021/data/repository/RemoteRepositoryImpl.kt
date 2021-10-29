package com.geek.android_trainee_task_2021.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.geek.android_trainee_task_2021.BuildConfig.API_KEY
import com.geek.android_trainee_task_2021.common.Constants
import com.geek.android_trainee_task_2021.common.network.Resource
import com.geek.android_trainee_task_2021.data.remote.OpenWeatherApi
import com.geek.android_trainee_task_2021.data.remote.dto.mapToCurrentWeather
import com.geek.android_trainee_task_2021.data.remote.dto.mapToDailyWeather
import com.geek.android_trainee_task_2021.domain.model.CurrentWeather
import com.geek.android_trainee_task_2021.domain.model.DailyWeather
import com.geek.android_trainee_task_2021.domain.model.Latlng
import com.geek.android_trainee_task_2021.domain.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi
) : RemoteRepository {

    override fun getCurrentWeather(cityName: String): LiveData<Resource<CurrentWeather>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = api.getCurrentWeather(cityName, Constants.units, Constants.lang, API_KEY)

            emit(
                if (response.isSuccessful && response.body() != null)
                    Resource.success(
                        response.body()?.mapToCurrentWeather(),
                        response.code()
                    )
                else Resource.error(
                    response.message(),
                    null,
                    response.code()
                )
            )
        }

    override fun getDailyWeather(latlng: Latlng): LiveData<Resource<DailyWeather>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))

            val response = api.getDailyWeather(
                latlng.lat.toString(),
                latlng.lon.toString(),
                Constants.exclude,
                Constants.units,
                API_KEY
            )
            emit(
                if (response.isSuccessful && response.body() != null)
                    Resource.success(
                        response.body()?.mapToDailyWeather(),
                        response.code()
                    )
                else Resource.error(
                    response.message(),
                    null,
                    response.code()
                )
            )
        }
}
