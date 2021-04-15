package com.example.mainscreen

import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mainscreen.databinding.ActivityMainBinding
import com.example.mainscreen.model.Forecast
import com.example.mainscreen.model.WeatherRepositoryImpl
import com.example.mainscreen.model.WeatherResponse
import com.example.mainscreen.view.WeatherScrollAdapter
import com.example.mainscreen.view.WeatherWeeklyAdapter
import com.example.mainscreen.viewmodel.WeatherViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewmodel: WeatherViewModel by lazy{
        WeatherViewModel.WeatherViewModelProvider(DI.provideRepository()).create(
            WeatherViewModel::class.java
        )
    }
    private lateinit var scrollAdapter: WeatherScrollAdapter
    private lateinit var weeklyAdapter: WeatherWeeklyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initViews()

        viewmodel.weatherForecast.observe(this){
            when(it){
                is WeatherRepositoryImpl.WeatherState.Response-> updateData(it.data)
                is WeatherRepositoryImpl.WeatherState.Error-> showError(it.errorMessage)
                is WeatherRepositoryImpl.WeatherState.Loading -> showLoading()
            }
        }
        viewmodel.getWeatherForecast()
    }

    private fun initViews(){
        binding.weeklyForecast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        binding.forecast.layoutManager = LinearLayoutManager(this)
    }

    private fun showLoading() {
        val currentVisiblity = binding.progressBar.visibility
        if(currentVisiblity == View.VISIBLE) binding.progressBar.visibility = View.GONE
        else binding.progressBar.visibility = View.VISIBLE
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun updateData(data: WeatherResponse) {
        weeklyAdapter = WeatherWeeklyAdapter(data){
            createForecastAdapter(it)
        }

        binding.textCity.text = data.city.name
        binding.textDate.text = DateFormat.getLongDateFormat(this).toString()
        binding.textDegrees.text = data.weather.days[0].low.toString() + "°"
        Picasso.get().load(data.city.imageURLs.androidImageURLs.xhdpiImageURL).into(binding.currentCity)

        binding.weeklyForecast.adapter = weeklyAdapter
    }

    private fun createForecastAdapter(data: Forecast) {
        scrollAdapter = WeatherScrollAdapter(data)
        binding.forecast.adapter = scrollAdapter
    }
}