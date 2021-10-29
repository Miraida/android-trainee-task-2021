package com.geek.android_trainee_task_2021.data.remote.dto

import com.geek.android_trainee_task_2021.domain.model.DailyWeather
import com.geek.android_trainee_task_2021.domain.model.DaysWeather


data class DailyWeatherDto(
    val daily: List<Daily>?,
    val lat: Double?,
    val lon: Double?,
    val timezone: String?,
    val timezone_offset: Int?
)

data class Daily(
    val clouds: Int?,
    val dew_point: Double?,
    val dt: Long?,
    val feels_like: FeelsLike?,
    val humidity: Int?,
    val moon_phase: Double?,
    val moonrise: Int?,
    val moonset: Int?,
    val pop: Double?,
    val pressure: Int?,
    val rain: Double?,
    val snow: Double?,
    val sunrise: Int?,
    val sunset: Int?,
    val temp: Temp?,
    val uvi: Double?,
    val weather: List<WeatherDto>?,
    val wind_deg: Int?,
    val wind_gust: Double?,
    val wind_speed: Double?
)

data class FeelsLike(
    val day: Double?,
    val eve: Double?,
    val morn: Double?,
    val night: Double?
)

data class Temp(
    val day: Double?,
    val eve: Double?,
    val max: Double?,
    val min: Double?,
    val morn: Double?,
    val night: Double?
)

fun DailyWeatherDto.mapToDailyWeather(): DailyWeather {
    val daysWeather = ArrayList<DaysWeather>()

    this.daily?.forEach { i ->
        daysWeather.add(
            DaysWeather(
                i.dt,
                i.temp?.max,
                i.temp?.min,
                i.weather?.get(0)?.main,
                i.weather?.get(0)?.description,
                i.weather?.get(0)?.icon
            )
        )
    }
    return DailyWeather(
        this.timezone,
        daysWeather
    )
}