package com.geek.android_trainee_task_2021.ui.disconnect

import com.geek.android_trainee_task_2021.R
import com.geek.android_trainee_task_2021.common.base.BaseFragment
import com.geek.android_trainee_task_2021.databinding.FragmentNetworkBinding
import com.geek.android_trainee_task_2021.extension.checkNetwork
import com.geek.android_trainee_task_2021.ui.weather.WeatherFragment


class NetworkFragment : BaseFragment<FragmentNetworkBinding>() {

    override fun bind(): FragmentNetworkBinding {
        return FragmentNetworkBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
        ui.btnTryAgain.setOnClickListener {
            if (requireContext().checkNetwork())
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, WeatherFragment()).commit()
        }
    }

    override fun setupUI() {

    }

}