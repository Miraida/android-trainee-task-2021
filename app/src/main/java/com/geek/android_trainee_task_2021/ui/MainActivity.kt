package com.geek.android_trainee_task_2021.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geek.android_trainee_task_2021.R
import com.geek.android_trainee_task_2021.ui.weather.WeatherFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setView()
    }

    private fun setView() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, WeatherFragment()).commit()
    }
}