package com.ipsoft.weatherapp.domain.repository

import com.ipsoft.weatherapp.domain.util.Resource
import com.ipsoft.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}