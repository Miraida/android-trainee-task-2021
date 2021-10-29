package com.geek.android_trainee_task_2021.data.remote.dto

import com.geek.android_trainee_task_2021.domain.model.CurrentWeather
import com.geek.android_trainee_task_2021.domain.model.Latlng
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

fun CurrentWeatherDto.mapToCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        name = this.name,
        main = this.weather?.get(0)?.main,
        description = this.weather?.get(0)?.description,
        icon = this.weather?.get(0)?.icon,
        temp = this.main?.temp,
        temp_max = this.main?.temp_max,
        temp_min = this.main?.temp_min,
        sunrise = this.sys?.sunrise,
        sunset = this.sys?.sunset,
        humidity = this.main?.humidity,
        pressure = this.main?.pressure,
        speed = this.wind?.speed,
        dt = this.dt,
        Latlng(this.coord?.lat.toString(), this.coord?.lon.toString()),
        timeZone = this.timezone
    )
}