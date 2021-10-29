package com.geek.android_trainee_task_2021.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geek.android_trainee_task_2021.R
import com.geek.android_trainee_task_2021.extension.checkNetwork
import com.geek.android_trainee_task_2021.ui.disconnect.NetworkFragment
import com.geek.android_trainee_task_2021.ui.weather.WeatherFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setView()
    }

    private fun setView() {
        val fragment = if (this.checkNetwork())
            WeatherFragment()
        else
            NetworkFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment).commit()
    }

    override fun onBackPressed() {
        val bSh = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet))

        if (bSh.state != BottomSheetBehavior.STATE_HIDDEN)
            bSh.state = BottomSheetBehavior.STATE_HIDDEN
        else super.onBackPressed()
    }
}