package com.example.mainscreen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainscreen.R
import com.example.mainscreen.databinding.HourlyWeatherLayoutBinding
import com.example.mainscreen.model.Forecast
import com.example.mainscreen.model.HourlyWeather
import com.example.mainscreen.model.WeatherResponse
import com.squareup.picasso.Picasso

class WeatherScrollAdapter(private val dataSet: Forecast):
    RecyclerView.Adapter<WeatherScrollAdapter.WeatherScrollViewHolder>() {

    class WeatherScrollViewHolder(private val binding: HourlyWeatherLayoutBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(weather: HourlyWeather){



            binding.hourlyHumidity.text = (weather.humidity*100).toString() + "%"
            binding.hourlyRain.text = (weather.rainChance*100).toString() + "%"
            binding.hourlyTemp.text = weather.temperature.toString() + "°"
            binding.hourlyTime.text = timeTranslator(weather.hour)
            binding.hourlyWind.text = weather.windSpeed.toString()
            when(weather.weatherType){
                "sunny"->{
                    Picasso.get().load(R.drawable.ic_icon_weather_active_ic_sunny_active).into(binding.hourlyIcon)
                }
                "cloudy"->{
                    Picasso.get().load(R.drawable.ic_icon_weather_active_ic_cloudy_active).into(binding.hourlyIcon)
                }
                "heavyRain"->{
                    Picasso.get().load(R.drawable.ic_icon_weather_active_ic_heavy_rain_active).into(binding.hourlyIcon)
                }
                "lightRain"->{
                    Picasso.get().load(R.drawable.ic_icon_weather_active_ic_light_rain_active).into(binding.hourlyIcon)
                }
                "partlyCloudy"->{
                    Picasso.get().load(R.drawable.ic_icon_weather_active_ic_partly_cloudy_active).into(binding.hourlyIcon)
                }
                "snowSleet"->{
                    Picasso.get().load(R.drawable.ic_icon_weather_active_ic_snow_sleet_active).into(binding.hourlyIcon)
                }
            }
        }

        private fun timeTranslator(hour: Int): String{
            if(hour == 0) return "12AM"
            if(hour == 12) return "12PM"

            if(hour in 1..11){
                return hour.toString() + "AM"
            }

            return (hour-12).toString() + "PM"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WeatherScrollViewHolder(HourlyWeatherLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: WeatherScrollViewHolder, position: Int) {
        holder.onBind(dataSet.hourlyWeather[position])
    }

    override fun getItemCount() = dataSet.hourlyWeather.size
}