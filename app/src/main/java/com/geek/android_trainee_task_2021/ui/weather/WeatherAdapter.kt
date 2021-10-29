package com.geek.android_trainee_task_2021.ui.weather

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geek.android_trainee_task_2021.R
import com.geek.android_trainee_task_2021.databinding.ItemDailyWeatherRvBinding
import com.geek.android_trainee_task_2021.domain.model.DaysWeather
import com.geek.android_trainee_task_2021.extension.toDate
import com.geek.android_trainee_task_2021.extension.loadImage
import javax.inject.Inject

class WeatherAdapter @Inject constructor() :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private var list: List<DaysWeather> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun update(list: List<DaysWeather>){
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ui = ItemDailyWeatherRvBinding.bind(itemView)
        private val cxt = itemView.context

        fun bind(data: DaysWeather) {
            ui.rvDateTv.text = data.dt?.toDate(cxt.getString(R.string.day_date_format))
            data.icon?.let { ui.rvIcon.loadImage(it) }
            ui.rvMinMaxDegTv.text =
                cxt.getString(
                    R.string.up_down_deg,
                    data.max?.toInt().toString(),
                    data.min?.toInt().toString()
                )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_daily_weather_rv, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}