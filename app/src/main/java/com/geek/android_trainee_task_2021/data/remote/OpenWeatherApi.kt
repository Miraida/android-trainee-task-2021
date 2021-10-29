package com.geek.android_trainee_task_2021.data.remote

import com.geek.android_trainee_task_2021.data.remote.dto.CurrentWeatherDto
import com.geek.android_trainee_task_2021.data.remote.dto.DailyWeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city_name: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") api_key: String
    ): Response<CurrentWeatherDto>

    @GET("data/2.5/onecall")
    suspend fun getDailyWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exclude: String,
        @Query("units") units: String,
        @Query("appid") api_key: String
    ): Response<DailyWeatherDto>

}