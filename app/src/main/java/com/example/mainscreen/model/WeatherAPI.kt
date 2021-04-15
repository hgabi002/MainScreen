package com.example.mainscreen.model

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherAPI {
    //https://weather.exam.bottlerocketservices.com/cities/1
    @GET("cities/{currentCity}")
    suspend fun getForecast(
        @Path("currentCity")
        currentCity: Int
    ): Response<WeatherResponse>

    companion object{
        fun getWeatherApi() = Retrofit.Builder()
            .baseUrl("https://weather.exam.bottlerocketservices.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }
}