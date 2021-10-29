package com.geek.android_trainee_task_2021.ui.weather

import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.geek.android_trainee_task_2021.R
import com.geek.android_trainee_task_2021.common.base.BaseFragment
import com.geek.android_trainee_task_2021.common.network.Status
import com.geek.android_trainee_task_2021.databinding.WeatherFragmentBinding
import com.geek.android_trainee_task_2021.domain.model.CurrentWeather
import com.geek.android_trainee_task_2021.domain.model.DailyWeather
import com.geek.android_trainee_task_2021.domain.model.Latlng
import com.geek.android_trainee_task_2021.extension.*
import com.geek.android_trainee_task_2021.utils.DefaultSharedPref
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeatherFragment : BaseFragment<WeatherFragmentBinding>() {

    @Inject
    lateinit var pref: DefaultSharedPref

    @Inject
    lateinit var adapter: WeatherAdapter

    private var sunrise: Long? = null
    private var sunset: Long? = null
    private lateinit var city: String
    private lateinit var bottomSheet: BottomSheetBehavior<FrameLayout>
    private val vModel: WeatherViewModel by viewModels()

    override fun bind(): WeatherFragmentBinding {
        return WeatherFragmentBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
        loadWeather()

        vModel.progressBar.observe(viewLifecycleOwner, {
            ui.progressBar.visibility(it)
        })

        ui.cityLayout.setOnClickListener {
            bottomSheet.show()
        }

        ui.locationIv.setOnClickListener {
            checkText()
        }
    }

    private fun checkText() {
        if (ui.edtLocation.text.toString() != "") {
            city = ui.edtLocation.text.toString()

            ui.edtLocation.hideKeyboard()
            bottomSheet.hide()

            if (requireContext().checkNetwork()) loadWeather()
            else requireContext().errorDialog(404, getString(R.string.no_connection))

        } else ui.edtLocation.setHintTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.red
            )
        )
    }

    override fun setupUI() {
        city = pref.getStoredCityName()
        bottomSheet = BottomSheetBehavior.from(ui.bottomSheet)
        ui.rvDailyWeather.adapter = adapter
    }


    private fun loadWeather() {
        vModel.loadCurrentWeather(city).observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    vModel.progressBar.value = true
                }
                Status.SUCCESS -> {
                    vModel.progressBar.value = false

                    loadDailyWeather(it.data?.latlng)
                    setCurrentWeatherData(it.data)
                }
                Status.ERROR -> {
                    vModel.progressBar.value = false
                    requireContext().errorDialog(it.code, it.msg)
                    pref.clearStoredCityName()
                }
            }
        })

    }

    private fun loadDailyWeather(latlng: Latlng?) {
        if (latlng != null) {
            vModel.loadDailyWeather(latlng).observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.LOADING -> {
                        vModel.progressBar.value = true
                    }
                    Status.SUCCESS -> {
                        vModel.progressBar.value = false
                        setDailyWeatherData(it.data)
                    }
                    Status.ERROR -> {
                        vModel.progressBar.value = false
                        requireContext().errorDialog(it.code, it.msg)
                    }
                }
            })
        }
    }

    private fun setDailyWeatherData(data: DailyWeather?) {
        ui.sunriseTv.text = sunrise?.toTime(data?.timeZone.toString())
        ui.sunsetTv.text = sunset?.toTime(data?.timeZone.toString())

        data?.list?.let { adapter.update(it) }
    }

    private fun setCurrentWeatherData(data: CurrentWeather?) {
        sunrise = data?.sunrise
        sunset = data?.sunset
        ui.mainImage.setImage(isDay(sunset, sunrise))

        ui.weatherTv.text = data?.main
        data?.icon?.let { ui.weatherIv.loadImage(it) }
        ui.dateTv.text = data?.dt?.toDate(resources.getString(R.string.main_date_format))
        ui.locationTv.text = data?.name
        ui.degTv.text = data?.temp?.toInt().toString()
        ui.degDownTv.text = getString(R.string.deg_down, data?.temp_min?.toInt())
        ui.degUpTv.text = getString(R.string.deg_up, data?.temp_max?.toInt())
        ui.dayTimeTv.text = toDaytime(sunrise, sunset)
        (data?.humidity.toString() + getString(R.string.percentage)).also {
            ui.humidityTv.text = it
        }
        ui.pressureTv.text = getString(R.string.mBar, data?.pressure)
        ui.windTv.text = getString(R.string.m_s, data?.speed?.toInt())
        pref.setStoredCityName(data?.name.toString())

    }


}
