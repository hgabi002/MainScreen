package com.example.mainscreen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainscreen.databinding.HourlyWeatherLayoutBinding
import com.example.mainscreen.model.Forecast
import com.example.mainscreen.model.HourlyWeather
import com.example.mainscreen.model.WeatherResponse

class WeatherScrollAdapter(private val dataSet: Forecast):
    RecyclerView.Adapter<WeatherScrollAdapter.WeatherScrollViewHolder>() {

    class WeatherScrollViewHolder(private val binding: HourlyWeatherLayoutBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(weather: HourlyWeather){

            binding.hourlyHumidity.text = weather.humidity.toString()
            binding.hourlyRain.text = weather.rainChance.toString()
            binding.hourlyTemp.text = weather.temperature.toString()
            binding.hourlyTime.text = weather.hour.toString()
            binding.hourlyWind.text = weather.windSpeed.toString()
            when(weather.weatherType){
                "sunny"->{
                    //Picasso
                }
                "cloudy"->{}
                "heavyRain"->{}
                "lightRain"->{}
                "partlyCloudy"->{}
                "snowSleet"->{}
            }
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