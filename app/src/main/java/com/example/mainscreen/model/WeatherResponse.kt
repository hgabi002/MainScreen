package com.example.mainscreen.model

data class WeatherResponse(
    val city: City,
    val weather: Weather
)

data class City(
    val name: String,
    val imageURLs: ImageUrls
)

data class ImageUrls(
    val androidImageURLs: AndroidImageUrl
)

data class AndroidImageUrl(
    val hdpiImageURL: String,
    val mdpiImageURL: String,
    val xhdpiImageURL: String
)

data class Weather(
    val days: List<Forecast>
)

data class Forecast(
    val dayOfTheWeek: Int,
    val weatherType: String,
    val hourlyWeather: List<HourlyWeather>,
    val low: Int
)

data class HourlyWeather(
    val temperature: Int,
    val humidity: Float,
    val hour: Int,
    val rainChance: Float,
    val weatherType: String,
    val windSpeed: Float
)