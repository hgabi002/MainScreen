package com.example.mainscreen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainscreen.databinding.WeeklyWeatherLayoutBinding
import com.example.mainscreen.model.Forecast
import com.example.mainscreen.model.WeatherResponse

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
            binding.weeklyTemp.text = dataItem.low.toString()

            when(dataItem.weatherType){
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