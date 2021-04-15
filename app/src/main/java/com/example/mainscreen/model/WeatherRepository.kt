package com.example.mainscreen.model

interface WeatherRepository {
    suspend fun getForecast(): WeatherRepositoryImpl.WeatherState
}