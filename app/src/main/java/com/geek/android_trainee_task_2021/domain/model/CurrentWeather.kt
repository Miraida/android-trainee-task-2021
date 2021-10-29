package com.geek.android_trainee_task_2021.domain.model

data class CurrentWeather(
    val name: String?,
    val main: String?,
    val description: String?,
    val icon: String?,
    val temp: Double?,
    val temp_max: Double?,
    val temp_min: Double?,
    val sunrise: Long?,
    val sunset: Long?,
    val humidity: Int?,
    val pressure: Int?,
    val speed: Double?,
    val dt: Long?,
    val latlng: Latlng?,
    val timeZone:Long?
)
