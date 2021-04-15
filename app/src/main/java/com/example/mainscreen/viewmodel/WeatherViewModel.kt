package com.example.mainscreen.viewmodel

import androidx.lifecycle.*
import com.example.mainscreen.model.WeatherRepository
import com.example.mainscreen.model.WeatherRepositoryImpl
import com.example.mainscreen.model.WeatherResponse
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository): ViewModel() {

    class WeatherViewModelProvider(private val repository: WeatherRepository):
            ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WeatherViewModel(repository) as T
        }
    }

    private val weatherForecastMutable = MutableLiveData<WeatherRepositoryImpl.WeatherState>()

    val weatherForecast: LiveData<WeatherRepositoryImpl.WeatherState>
    get() = weatherForecastMutable

    fun getWeatherForecast(){
        viewModelScope.launch {
            weatherForecastMutable.value = WeatherRepositoryImpl.WeatherState.Loading
            val data = repository.getForecast()
            weatherForecastMutable.value = data
        }
    }
}