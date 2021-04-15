package com.example.mainscreen


import com.example.mainscreen.model.WeatherRepositoryImpl

object DI {
    fun provideRepository() = WeatherRepositoryImpl()
}
