package com.geek.android_trainee_task_2021.domain.model

data class DailyWeather(
    val timeZone: String?,
    val list: List<DaysWeather>?
)