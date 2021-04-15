package com.example.mainscreen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mainscreen.R
import com.example.mainscreen.databinding.WeeklyWeatherLayoutBinding
import com.example.mainscreen.model.Forecast
import com.example.mainscreen.model.WeatherResponse
import com.squareup.picasso.Picasso

class WeatherWeeklyAdapter(private val dataset: WeatherResponse,
    private val callback: (Forecast)->Unit): RecyclerView.Adapter<WeatherWeeklyAdapter.WeatherWeeklyViewHolder>() {

    class WeatherWeeklyViewHolder(private val binding: WeeklyWeatherLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(dataItem: Forecast, callback:(Forecast)-> Unit){
            binding.root.setOnClickListener {
                callback.invoke(dataItem)
            }
            binding.weeklyDay.text =
                when(dataItem.dayOfTheWeek){
                    0-> "MON"
                    1-> "TUE"
                    2-> "WED"
                    3-> "THUR"
                    4-> "FRI"
                    5-> "SAT"
                    else-> "SUN"
                }
            binding.weeklyTemp.text = dataItem.low.toString() + "°"

            when(dataItem.weatherType){
                "sunny"->{
                    Picasso.get().load(R.drawable.ic_icon_weather_active_ic_sunny_active).into(binding.weeklyIcon)
                }
                "cloudy"->{
                    Picasso.get().load(R.drawable.ic_icon_weather_active_ic_cloudy_active).into(binding.weeklyIcon)
                }
                "heavyRain"->{
                    Picasso.get().load(R.drawable.ic_icon_weather_active_ic_heavy_rain_active).into(binding.weeklyIcon)
                }
                "lightRain"->{
                    Picasso.get().load(R.drawable.ic_icon_weather_active_ic_light_rain_active).into(binding.weeklyIcon)
                }
                "partlyCloudy"->{
                    Picasso.get().load(R.drawable.ic_icon_weather_active_ic_partly_cloudy_active).into(binding.weeklyIcon)
                }
                "snowSleet"->{
                    Picasso.get().load(R.drawable.ic_icon_weather_active_ic_snow_sleet_active).into(binding.weeklyIcon)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WeatherWeeklyViewHolder(WeeklyWeatherLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: WeatherWeeklyViewHolder, position: Int) {
        holder.onBind(dataset.weather.days[position], callback)
    }

    override fun getItemCount() = dataset.weather.days.size
}