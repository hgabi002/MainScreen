package com.example.mainscreen.model


class WeatherRepositoryImpl: WeatherRepository {
    private val api = WeatherAPI.getWeatherApi()
    override suspend fun getForecast(): WeatherState{
        val data = api.getForecast(1)
        return if (data.isSuccessful)
            WeatherState.Response(data.body()!!)
        else
            WeatherState.Error(data.message())
    }



    sealed class WeatherState{
        data class Response(val data: WeatherResponse): WeatherState()
        object Loading: WeatherState()
        data class Error(val errorMessage: String): WeatherState()
    }
}