package com.geek.android_trainee_task_2021.ui.weather

import android.util.Log
import com.geek.android_trainee_task_2021.common.base.BaseFragment
import com.geek.android_trainee_task_2021.databinding.WeatherFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : BaseFragment<WeatherFragmentBinding>() {


    override fun bind(): WeatherFragmentBinding {
        return WeatherFragmentBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
    }

    override fun setupUI() {
        Log.e("TAG", "setupUI: ")
    }

}