package com.geek.android_trainee_task_2021.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDto(
    val base: String?,
    val clouds: Clouds?,
    val cod: Int?,
    val coord: Coord?,
    val dt: Long?,
    val id: Int?,
    val main: Main?,
    val name: String?,
    val sys: Sys?,
    val timezone: Long?,
    val visibility: Int?,
    val weather: List<WeatherDto>?,
    val wind: Wind?
)

data class Clouds(
    val all: Int?
)

data class Coord(
    val lat: Double?,
    val lon: Double?
)

data class Main(
    val feels_like: Double?,
    val grnd_level: Int?,
    val humidity: Int?,
    val pressure: Int?,
    val sea_level: Int?,
    val temp: Double?,
    val temp_max: Double?,
    val temp_min: Double?
)

data class Sys(
    val country: String?,
    val id: Int?,
    val sunrise: Long?,
    val sunset: Long?,
    val type: Int?
)

data class WeatherDto(
    val id: Int?,
    val main: String?,
    val description: String?,
    val icon: String?
)

data class Wind(
    @SerializedName("deg")
    val deg: Int?,
    @SerializedName("gust")
    val gust: Double?,
    @SerializedName("speed")
    val speed: Double?
)